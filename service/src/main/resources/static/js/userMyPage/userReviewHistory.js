function showModifyBox(num) {
    var ReviewBox = document.getElementById("ReviewBox" + num);
    var modifyReview = document.getElementById("modifyReview" + num);
    var btns = document.getElementsByClassName("modifyBtn");
    var content = document.getElementById("reviewContent" + num);
    var newReviewContent = document.getElementById("newReviewContent" + num);

    $("#newRating"+num).val($("#oldRating"+num).val());

    newReviewContent.value = content.textContent;

    for (let i = 0; i < btns.length; i++) {
        if (btns.item(i).className === ("modifyBtn" + num)) {
            // do nothing
        } else {
            btns.item(i).disabled = true;
        }
    }

    ReviewBox.style.display = "none";
    modifyReview.style.display="block";
}

function newStar(rating, num) {
    var newRating = document.getElementById("newRating"+num);
    var star = document.getElementById("star"+num);
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

function cancelNewReview(num) {
    var ReviewBox = document.getElementById("ReviewBox" + num);
    var modifyReview = document.getElementById("modifyReview" + num);

    ReviewBox.style.display = "block";
    modifyReview.style.display="none";

    var btns = document.getElementsByClassName("modifyBtn");


    for (let i = 0; i < btns.length; i++) {
            btns.item(i).disabled = false;
    }

    var oldRating = document.getElementById("oldRating"+num);
    var newRating = document.getElementById("newRating"+num);
    var star = document.getElementById("star"+num);

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

function getFileName(reviewId, num) {
    var fileName = $("#reviewFile"+ reviewId + num).val();
    var cleanName = fileName.substring(12);
    $(".fileName"+ reviewId + num).val(cleanName);
}

function removeImage(reviewId, num) {
    $("#reviewFile"+ reviewId + num).val(null);
    $(".fileName"+ reviewId + num).val('첨부파일')
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

var BigOneReviewBoxs = document.querySelectorAll(".BigOneReviewBox");
var reviewCount = document.getElementById("reviewCount");
if (BigOneReviewBoxs.length>0) {
    reviewCount.innerHTML = "총 리뷰 갯수 ("+BigOneReviewBoxs.length + ")";
}


function getFileName(num) {
    var fileName = $("#reviewFile" + num).val();
    var cleanName = fileName.substring(12);
    $(".fileName" + num).val(cleanName);
}

function removeImage(num) {
    console.log(num);

    $("#reviewFile" + num).val(null);
    $(".fileName" + num).val('첨부파일');
}
