let itemsCount;
let items = 2;
let num = 1;

function showMoreBtn() {
    var divs = document.querySelectorAll('.productOneBlock');
    var moreBtn = document.getElementById("moreBtn");


    if ($("#itemCount").val() > 12) {
        moreBtn.style.display = "inline";

        if (divs.length == $("#itemCount").val()) {
            moreBtn.style.display = "none";
        }

    } else {
        moreBtn.style.display = "none";
    }
}

showMoreBtn();


function getItems() {
    itemsCount = items * num;

    $.ajax({
        url: "/api/items",
        type: "post",
        data: {
            page: num
        },
        dataType: "json",
        success: function (datas) {

            var result = datas['result'];

            if (result.length > 0) {
                for (let i = 0; i < result.length; i++) {
                    var content = "";

                    content = "<a href='/item/" + result[i].id + "'><div class='productOneBlock'>" +
                        "<ul><div><div>" +
                        "<li class='productPicture'><img class='productImg' src='" + result[i].imageUrl + "' alt='" + result[i].itemName + "'></li>" +
                        "<li class='productName_Status'><div class='itemTitle' style='display: flex;'>";

                    if (result[i].itemStatus === "SOLD_OUT") {
                        content += "<div class='productStatus' style='margin-right: 5px;'>품절</div>";
                        content += "<div class='productName'  title='" + result[i].itemName + "'>" + result[i].itemName + "</div></div></li>";

                    } else {
                        content += "<div class='productName' title='" + result[i].itemName + "'>" + result[i].itemName + "</div></div></li>";
                    }

                    content += "<li class='productDetail' title='상품 내용'>" + result[i].itemDetail + "</li>" +
                        "<li class='productStar'>";

                    if (result[i].totalRating == 5) {
                        content += "<img class='star' src='/images/star5_0.png'>";

                    } else if (result[i].totalRating == 4.5) {
                        content += "<img class='star' src='/images/star4_5.png'>";

                    } else if (result[i].totalRating == 4) {
                        content += "<img class='star' src='/images/star4_0.png'>";

                    } else if (result[i].totalRating == 3.5) {
                        content += "<img class='star' src='/images/star3_5.png'>";

                    } else if (result[i].totalRating == 3) {
                        content += "<img class='star' src='/images/star3_0.png'>";

                    } else if (result[i].totalRating == 2.5) {
                        content += "<img class='star' src='/images/star2_5.png'>";

                    } else if (result[i].totalRating == 2) {
                        content += "<img class='star' src='/images/star2_0.png'>";

                    } else if (result[i].totalRating == 1.5) {
                        content += "<img class='star' src='/images/star1_5.png'>";

                    } else if (result[i].totalRating == 1) {
                        content += "<img class='star' src='/images/star1_0.png'>";

                    } else if (result[i].totalRating == 0.5) {
                        content += "<img class='star' src='/images/star0_5.png'>";

                    } else if (result[i].totalRating == 0) {
                        content += "<img class='star' src='/images/star5_0.png'>";

                    }

                    content += "<span class='reviewCount' style='margin-left: 5px;'>(" + result[i].reviewCount + ")</span>";
                    content += "</li></div>";
                    content += "<div><li class='productPriceDetail'>" +
                        "<span class='originalPrice' style='margin-right: 5px;'>" + result[i].originalPrice + "</span>";

                    if (result[i].discountRate != 0) {
                        content += "<span class='discountRate'>(" + result[i].discountRate + "%)</span>"
                    }
                    content += "<span class='productPrice'>" + result[i].price + "</span><span class='won'>원</span></li></div>";
                    content += "</div></ul></div></a>"

                    $(".mainContainer").append(content);
                }
            }
            num++;
            showMoreBtn();
        }
    })
}



