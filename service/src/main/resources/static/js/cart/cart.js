var totalPrice = parseInt($(".totalPriceTxt").text()) - parseInt($(".deliveryPriceTxt").text()) - parseInt($(".totalDiscountPriceTxt").text());
$(".paymentPriceTxt").text(totalPrice);

function minusItem(itemId) {
    var count = document.getElementById("itemAmount" + itemId);
    var price = document.getElementById("itemPrice" + itemId);

    var num = parseInt(count.value);
    var cost = parseInt(price.textContent);

    if (num > 1) {
        num = num - 1;
        count.value = num;

        $(".totalPriceTxt").text(parseInt($(".totalPriceTxt").text()) - cost);

        var totalPrice = parseInt($(".totalPriceTxt").text()) - parseInt($(".deliveryPriceTxt").text()) - parseInt($(".totalDiscountPriceTxt").text());

        $(".paymentPriceTxt").text(totalPrice);
        $("#submitPrice").val((totalPrice + '원 구매하기'));
    }
    updateCartItem(itemId, count);
}

function plusItem(itemId) {
    var count = document.getElementById("itemAmount" + itemId);
    var price = document.getElementById("itemPrice" + itemId);

    var num = parseInt(count.value);
    var cost = parseInt(price.textContent);

    if (num >= 1) {
        num = num + 1;
        count.value = num;

        $(".totalPriceTxt").text(parseInt($(".totalPriceTxt").text()) + cost);

        var totalPrice = parseInt($(".totalPriceTxt").text()) - parseInt($(".deliveryPriceTxt").text()) - parseInt($(".totalDiscountPriceTxt").text());

        $(".paymentPriceTxt").text(totalPrice);
        $("#submitPrice").val((totalPrice + '원 구매하기'));
    }
    updateCartItem(itemId, count);

}

// cart.js

function updateCartItem(itemId, count) {

    let csrfToken = $('meta[name="_csrf"]').attr('content');
    let csrfHeader = $('meta[name="_csrf_header"]').attr('content');
    let headers = {};
    headers[csrfHeader] = csrfToken;

    let cartItemUpdateRequest = {
        itemId: itemId,
        cartItemCount: count.value
    };

    $.ajax({
        url: '/cart/update',
        type: 'POST',
        headers: headers,
        data: JSON.stringify(cartItemUpdateRequest),
        contentType: 'application/json',
        success: function (data) {

        },
        error: function (xhr, status, error) {
            console.error(error);
            console.log(status);
            console.log(xhr.responseText);
            if (xhr.status === 400) {
                alert('재고 부족으로 인해 장바구니 담기에 실패하였습니다.');
                return;
            }
            alert('잠시 후 다시 시도해주세요.');
        }
    });


}


