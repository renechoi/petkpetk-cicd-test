var addItemContainer = document.getElementById("addItemContainer");


function getFileName(num) {
    var fileName = $("#itemFile" + num).val();
    var cleanName = fileName.substring(12);
    $(".fileName" + num).val(cleanName);
}

function removeImage(num) {
    $("#itemFile" + num).val(null);
    if (num == 1) {
        $(".fileName" + num).val('대표 이미지')
    } else {
        $(".fileName" + num).val('첨부파일')
    }
}


var addForm = document.getElementById("addForm");
var firstImage = document.getElementById("firstImage");
var addItemName = document.getElementById("addItemName");
var addItemPrice = document.getElementById("itemPrice");
var addAmount = document.getElementById("itemAmount");
var addItemDetail = document.getElementById("itemDetail");

addForm.addEventListener('submit', function (event) {

    if (addItemName == null || addItemName.value == "") {
        alert("상품명을 입력해주세요.");
        event.preventDefault();
        addItemName.scrollIntoView({behavior: "smooth", block: "start"});
        addItemName.focus();
        return false;
    }

    if (addItemPrice == null || addItemPrice.value == "") {
        alert("상품 가격을 정해주세요.");
        event.preventDefault();
        addItemPrice.scrollIntoView({behavior: "smooth", block: "start"});
        addItemPrice.focus();
        return false;
    }

    if (addAmount == null || addAmount.value == "") {
        alert("상품 재고를 입력해주세요.");
        event.preventDefault();
        addAmount.scrollIntoView({behavior: "smooth", block: "start"});
        addAmount.focus();
        return false;
    }

    if (addItemDetail == null || addItemDetail.value == "") {
        alert("상품 상세 정보를 입력해주세요.");
        event.preventDefault();
        addItemDetail.scrollIntoView({behavior: "smooth", block: "start"});
        addItemDetail.focus();
        return false;
    }

    if (firstImage == null || firstImage.value == "대표 이미지") {
        alert("대표 이미지를 정해주세요.");
        firstImage.scrollIntoView({behavior: "smooth", block: "start"});
        event.preventDefault();
        return false;
    }

    if (addItemDetail.value.length > 5000) {
        alert("상세 내용은 5000자 이하로 입력해주세요.");
        addItemDetail.scrollIntoView({behavior: "smooth", block: "start"});
        addItemDetail.focus();
        event.preventDefault();
        return false;
    }

});


const AC = document.querySelector(".addItemContainer");
window.addEventListener("scroll", () => {
    var width = window.innerWidth;

    if (!addItemContainer) {

    } else {

        if (window.scrollY > headerHeight && width < 880) {
            AC.classList.add("jumped3");
            AC.classList.remove("jumped4");
        } else if (window.scrollY > headerHeight && width > 880) {
            AC.classList.remove("jumped3");
            AC.classList.add("jumped4");
        } else {

            AC.classList.remove("jumped4");
            AC.classList.remove("jumped3");
        }
    }
});

$(document).ready(function() {
    var itemDetail = document.getElementById("itemDetail");
    var textCount = document.getElementById("textCount");

    var textLength = itemDetail.textContent.length;
    console.log(textLength);

    textCount.innerText=textLength+"/5000";

});
function countText() {
    var itemDetail = document.getElementById("itemDetail");
    var textCount = document.getElementById("textCount");

    var textLength = itemDetail.value.length;

    textCount.innerText=textLength+"/5000";

    if (textLength > 5000) {
        textCount.style.color = "red";
        itemDetail.style.backgroundColor = "#fff3f7";
    } else {
        textCount.style.color = "#b08f8f";
        itemDetail.style.backgroundColor = "white";
    }

}

$("#discountRate").on("change", function () {
    $("#price").val(Math.round($("#itemPrice").val() - $("#itemPrice").val() * $("#discountRate").val()));
});

$("#itemPrice").on("keyup", function () {
    $("#price").val(Math.round($("#itemPrice").val() - $("#itemPrice").val() * $("#discountRate").val()));

});