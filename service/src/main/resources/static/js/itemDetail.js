var price = document.getElementById("price");
var count = document.getElementById("orderCount");
var totalPrice = document.getElementById("totalPrice");
var itemDetailContainer = document.getElementById("itemDetailContainer");
var userEmail = document.getElementById("userEmail");


function setcount(){
    $("#orderCount").val(1);
}

setcount();

function minusLike(num, likesNum) {
    clickLikes(num, likesNum, userEmail);
}

function minusCount() {
    var num = parseInt(count.value);
    var cost = parseInt(price.value);
    if (num > 1) {
        num = num - 1;
        count.value = num;
        totalPrice.innerHTML = cost * num;
    }
}

function plusCount() {
    var num = parseInt(count.value);
    var cost = parseInt(price.value);
    num = num + 1;
    count.value = num;
    totalPrice.innerHTML = cost * num;
}

const DC = document.querySelector(".itemDetailContainer");
window.addEventListener("scroll", () => {
    var width = window.innerWidth;

    if (!itemDetailContainer) {

    } else {

        if (window.scrollY > headerHeight && width < 880) {
            DC.classList.add("jumped3");
            DC.classList.remove("jumped4");
        } else if (window.scrollY > headerHeight && width > 880) {
            DC.classList.remove("jumped3");
            DC.classList.add("jumped4");
        } else {

            DC.classList.remove("jumped4");
            DC.classList.remove("jumped3");
        }
    }
});

function showDetailBoxs(e) {
    var detailBtn = document.getElementById("detailBtn");
    var review = document.getElementById("reviewBtn");
    var business = document.getElementById("businessBtn");
    var detailBox = document.getElementById("detailBox");
    var reviewBox = document.getElementById("reviewBox");
    var businessBox = document.getElementById("businessBox");

    if (e == detailBtn) {
        detailBox.style.display = "block";
        reviewBox.style.display = "none";
        businessBox.style.display = "none";
    } else if (e == review) {
        detailBox.style.display = "none";
        reviewBox.style.display = "block";
        businessBox.style.display = "none";
    } else if (e == business) {
        detailBox.style.display = "none";
        reviewBox.style.display = "none";
        businessBox.style.display = "block";
    }

}

function likesMouseOn(num) {

    if ($("#likes" + num + "").attr("src") == "/images/likesBefore.png") {
        $("#likes" + num + "").attr("src", "/images/likesHover.png");
    } else {
        $("#likes" + num + "").attr("src", "/images/likesBefore.png");
    }
}

function intoLikeOnclick(num) {
    $("#likes" + num).attr("onclick", "clickLikes(" + num + ", '" + userEmail.value + "')");
}

function clickLikes(num, email) {

    var likeCount = document.getElementById("likeCount" + num);
    if ($("#likes" + num + "").attr("onmouseover") == "intoLikeOnclick(" + num + ")") {

        var intlike = parseInt(likeCount.value);
        var like = document.getElementById("reviewLikes" + num + "")
        $("#likes" + num + "").attr("onmouseover", "likesMouseOn(" + num + "); intoLikeOnclick(" + num + ")");
        $("#likes" + num + "").attr("onmouseout", "likesMouseOn(" + num + ")");
        $("#likes" + num + "").attr("src", "/images/likesHover.png");

        $.ajax({
            url: "/api/review/like",
            type: "post",
            data: {
                num: -1,
                reviewId: num,
                likeNum: intlike,
                userEmail: email
            },
            dataType: "parameters",
            success: function (isPlus) {
                if (isPlus == true) {

                } else {
                    like.innerHTML = "좋아요 취소 실패";
                }
            },
            error: function (XHR, status, error) {
            }
        })
        likeCount.value = intlike - 1;
        like.innerHTML = intlike - 1;

    } else if ($("#likes" + num + "").attr("onmouseover") == "likesMouseOn(" + num + "); intoLikeOnclick(" + num + ")") {
        var like = document.getElementById("reviewLikes" + num + "")

        $("#likes" + num + "").attr("onmouseover", "intoLikeOnclick(" + num + ")");
        $("#likes" + num + "").attr("onmouseout", "");
        $("#likes" + num + "").attr("src", "/images/likesAfter.png");

        var likeCount = document.getElementById("likeCount" + num);
        var intlike = parseInt(likeCount.value);

        $.ajax({
            url: "/api/review/like",
            type: "post",
            data: {
                num: 1,
                reviewId: num,
                likeNum: intlike,
                userEmail: email
            },
            dataType: "parameters",
            success: function (isPlus) {
                if (isPlus == true) {
                } else {
                    like.innerHTML.innerHTML = "좋아요 추가 실패";
                }
            },
            error: function (XHR, status, error) {
            }
        })
        likeCount.value = intlike + 1;
        like.innerHTML = intlike + 1;
    }
}


function showReviewModal(reviewId, itemId) {
    var reviewModalForm = document.getElementById("reviewModalForm");
    var reviewModalContent = document.getElementById("reviewModalContent");
    var reviewContent = document.getElementById("reviewContent" + reviewId + "");
    var cancelModify = document.getElementById("cancelModify");


    cancelModify.setAttribute("onclick", "closeReviewModal(" + reviewId + "); cancelNewReview();")
    reviewModalContent.value = reviewContent.textContent;
    reviewModalForm.setAttribute("action", "/review/modify/" + itemId + "/" + reviewId);
    reviewModalForm.style.display = "block";

}

function closeReviewModal(reviewId) {
    var reviewModalForm = document.getElementById("reviewModalForm");
    var reviewModalContent = document.getElementById("reviewModalContent");
    var reviewContent = document.getElementById("reviewContent" + reviewId + "");
    console.log(reviewContent.textContent);

    reviewModalContent.value = reviewContent.textContent;
    reviewModalForm.style.display = "none";
}

function getFileName(num) {
    var fileName = $("#reviewFile" + num).val();
    var cleanName = fileName.substring(12);
    $(".fileName" + num).val(cleanName);
}

function removeImage(num) {
    $("#reviewFile" + num).val(null);
    $(".fileName" + num).val('첨부파일')
}

var imageIdx = 0;

function getPath(e, reviewId) {
    var images = document.getElementsByClassName("reviewImages" + reviewId);
    var path = $(e).attr('src');
    popImage(path, reviewId);
    imageIdx = Array.prototype.indexOf.call(images, e);
    if (images.length == 1) {
        $(".previousImageBtn").css("display", "none");
        $(".nextImageBtn").css("display", "none");
    } else {
        if (imageIdx == (images.length - 1)) {
            $(".nextImageBtn").css("display", "none");
        } else if (imageIdx == 0) {
            $(".previousImageBtn").css("display", "none");
        }
    }

}

function popImage(path, reviewId) {
    $(".image-wrap" + reviewId).css("display", "flex").show();
    $(".images" + reviewId)
        .html("<div class='btnPlace'><input type='button' value='◀' class='previousImageBtn' onclick='preImage(" + reviewId + ")'></div><div class='imageZomBox'><input class='closeBtn' type='button' value='✖' onclick='closeImage(" + reviewId + ")'><img src='" + path + "' class='zomInImage'></div><div class='btnPlace'><input type='button' value='▶' class='nextImageBtn' onclick='nextImage(" + reviewId + ")'></div>")
        .css({width: '100%', height: '100%'});

}

$("#newRating").val($("#oldRating").val());

function preImage(reviewId) {
    var images = document.getElementsByClassName("reviewImages" + reviewId);
    imageIdx--;

    var path = images[imageIdx].getAttribute("src");
    console.log(path);
    $(".zomInImage").attr("src", path);

    if (imageIdx == 0) {
        $(".previousImageBtn").css("display", "none");
        $(".nextImageBtn").css("display", "block");
    } else {
        $(".previousImageBtn").css("display", "block");
        $(".nextImageBtn").css("display", "block");
    }
}

function nextImage(reviewId) {
    var images = document.getElementsByClassName("reviewImages" + reviewId);
    imageIdx++;

    var path = images[imageIdx].getAttribute("src");
    console.log(path);
    console.log(imageIdx);
    console.log(images.length);
    $(".zomInImage").attr("src", path);

    if (imageIdx == (images.length - 1)) {
        $(".nextImageBtn").css("display", "none");
        $(".previousImageBtn").css("display", "block");
    } else {
        $(".previousImageBtn").css("display", "block");
        $(".nextImageBtn").css("display", "block");
    }

}

function closeImage(reviewId) {
    $(".images" + reviewId).animate({width: '0%', height: '0%'});
    $(".image-wrap" + reviewId).hide();
    $(".previousImageBtn").css("display", "");
    $(".nextImageBtn").css("display", "");
    imageIdx = 0;
}


function newStar(rating) {
    var newRating = document.getElementById("newRating");
    var star = document.getElementById("star");
    newRating.value = rating;

    if (rating == 0.5) {
        star.setAttribute("src", "/images/star0_5.png")

    } else if (rating == 1) {
        star.setAttribute("src", "/images/star1_0.png")

    } else if (rating == 1.5) {
        star.setAttribute("src", "/images/star1_5.png")

    } else if (rating == 2) {
        star.setAttribute("src", "/images/star2_0.png")

    } else if (rating == 2.5) {
        star.setAttribute("src", "/images/star2_5.png")

    } else if (rating == 3) {
        star.setAttribute("src", "/images/star3_0.png")

    } else if (rating == 3.5) {
        star.setAttribute("src", "/images/star3_5.png")

    } else if (rating == 4) {
        star.setAttribute("src", "/images/star4_0.png")

    } else if (rating == 4.5) {
        star.setAttribute("src", "/images/star4_5.png")

    } else if (rating == 5) {
        star.setAttribute("src", "/images/star5_0.png")

    }

}

function cancelNewReview() {
    var oldRating = document.getElementById("oldRating");
    var newRating = document.getElementById("newRating");
    var star = document.getElementById("star");

    if (oldRating.value == 0.5) {
        star.setAttribute("src", "/images/star0_5.png")
        newRating.value = oldRating.value;

    } else if (oldRating.value == 1) {
        star.setAttribute("src", "/images/star1_0.png")
        newRating.value = oldRating.value;

    } else if (oldRating.value == 1.5) {
        star.setAttribute("src", "/images/star1_5.png")
        newRating.value = oldRating.value;

    } else if (oldRating.value == 2) {
        star.setAttribute("src", "/images/star2_0.png")
        newRating.value = oldRating.value;

    } else if (oldRating.value == 2.5) {
        star.setAttribute("src", "/images/star2_5.png")
        newRating.value = oldRating.value;

    } else if (oldRating.value == 3) {
        star.setAttribute("src", "/images/star3_0.png")
        newRating.value = oldRating.value;

    } else if (oldRating.value == 3.5) {
        star.setAttribute("src", "/images/star3_5.png")
        newRating.value = oldRating.value;

    } else if (oldRating.value == 4) {
        star.setAttribute("src", "/images/star4_0.png")
        newRating.value = oldRating.value;

    } else if (oldRating.value == 4.5) {
        star.setAttribute("src", "/images/star4_5.png")
        newRating.value = oldRating.value;

    } else if (oldRating.value == 5) {
        star.setAttribute("src", "/images/star5_0.png")
        newRating.value = oldRating.value;

    }

}



function handleOrderButtonClick(event) {
    event.preventDefault();
    redirectLoginPage();
}

function redirectLoginPage() {
    alert("먼저 로그인을 해주세요");
    window.location.href = '/login';
}

function proceedCheckout(){
    $("#priceInfoTotalPrice").val($("#totalPrice").text());
    getOrderItemId();
}

function getOrderItemId(){
    $("#orderItemId").val($("#itemId").val());
}


function addCart(){

    let csrfToken = $('meta[name="_csrf"]').attr('content');
    let csrfHeader = $('meta[name="_csrf_header"]').attr('content');
    let headers = {};
    headers[csrfHeader] = csrfToken;
    let cartItemRequest = {
        itemId: $('#itemId').val(),
        cartItemCount:  $('#orderCount').val()
    };


    $.ajax({
        url: '/cart',
        type: 'POST',
        headers: headers,
        data: JSON.stringify(cartItemRequest),
        contentType: 'application/json',
        success: function (data ) {
            $('#message').text('장바구니에 담겼습니다.');
            $('#cart-result-modal').show();

            $('.btn-secondary').on('click', closeModal);
            $('.close').on('click', closeModal);
        },
        error: function (xhr, status, error) {
            console.error(error);
            console.log(status);
            console.log(xhr.responseText);
            if (xhr.status === 401) {
                alert('로그인이 필요합니다.');
                window.location.href = '/login';
                return;
            }
            if (xhr.status === 400) {
                alert('재고 부족으로 인해 장바구니 담기에 실패하였습니다.');
                return;
            }
            if (xhr.status === 409) {
                alert('상품이 장바구니에 이미 존재합니다.');
                return;
            }
            alert('잠시 후 다시 시도해주세요.');
        }
    });
}

function closeModal() {
    $('#cart-result-modal').hide();
}

$(document).keyup(function(e) {
    if (e.key === "Escape") { // esc key maps to keycode `27`
        closeModal();
    }
});

function showImageDetail(e) {
    $("#itemRepresentativeImageDetail").attr("src", e.getAttribute("src"));
    $("#itemImagesDetailBox").css("display", "block");
}

function closeItemImageDetail() {
    $("#itemImagesDetailBox").css("display", "none");
}