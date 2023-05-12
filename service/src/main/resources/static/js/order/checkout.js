function mapPaymentRequest() {
    console.log("check")
    $("#paymentItemName").val($(".itemName").text());
    $("#paymentFinalPaymentPrice").val($("#finalPaymentPrice").val());
}

mapPaymentRequest();


// ============================================================


$("#getNewAddress").on("click", function () {
    $("#newAddress").css("display", "block");
    $("#getNewAddress").addClass("choose");

    $("#oldAddress").css("display", "none");
    $("#getOldAddress").removeClass("choose");

    $("#shippingName").attr("name", "shippingName")
    $("#recipientName").attr("name", "recipientName")
    $("#contactNumber").attr("name", "contactNumber")
    $("#shippingAddress").attr("name", "shippingAddress")

    $("#oldShippingName").attr("name", "")
    $("#oldRecipientName").attr("name", "")
    $("#oldContactNumber").attr("name", "")
    $("#oldShippingAddress").attr("name", "")


});
$("#getOldAddress").on("click", function () {
    $("#newAddress").css("display", "none");
    $("#getNewAddress").removeClass("choose");


    $("#oldAddress").css("display", "block");
    $("#getOldAddress").addClass("choose");

    $("#shippingName").attr("name", "")
    $("#recipientName").attr("name", "")
    $("#contactNumber").attr("name", "")
    $("#shippingAddress").attr("name", "")

    $("#oldShippingName").attr("name", "shippingName")
    $("#oldRecipientName").attr("name", "recipientName")
    $("#oldContactNumber").attr("name", "contactNumber")
    $("#oldShippingAddress").attr("name", "shippingAddress")
});


var checkOutForm = document.getElementById("checkOutForm");

checkOutForm.addEventListener("submit", function (event) {
    event.preventDefault();

    if ($("input[name='shippingName']").val() == "") {
        alert("배송지 정보를 확인해주세요.");
        return false;
    }

    if ($("input[name='recipientName']").val() == "") {
        alert("배송지 정보를 확인해주세요.");
        return false;
    }

    if ($("input[name='contactNumber']").val() == "") {
        alert("배송지 정보를 확인해주세요.");
        return false;
    }

    if ($("#oldAddress").css("display") == "none") {
        if ($("#detailAddress").val() == "") {
            alert("상세 주소를 적어주세요.");
            return false;
        }
        $("input[name='shippingAddress']").val('('+$("#zipCode").val()+') '+ $("#addr").val() + ' ' + $("#detailAddress").val() + ' ' + $("#extraAddress").val());
    }

    if ($("input[name='shippingAddress']").val() == "") {
        alert("배송지 정보를 확인해주세요.");
        return false;
    }

    if (!$('#all-agreements').prop('checked')) {
        alert("개인 정보 수집 및 결재 사항을 동의해주세요.");
        return false;
    }




    checkOutForm.submit();
});


// 전체 동의 체크박스가 클릭될 때
$('#all-agreements').click(function () {
    if (this.checked) {
        // 전체 동의 체크박스가 체크된 경우
        // 모든 체크박스를 체크하고 all-agreements도 체크
        $('.checking').prop('checked', true);
        $('#all-agreements').prop('checked', true);
    } else {
        // 전체 동의 체크박스가 체크 해제된 경우
        // 모든 체크박스의 체크를 해제하고 all-agreements도 체크 해제
        $('.checking').prop('checked', false);
        $('#all-agreements').prop('checked', false);
    }
});

$('.checking').click(function () {
    if ($('.checking:checked').length === $('.checking').length) {
        // 모든 체크박스가 체크된 경우 all-agreements도 체크
        $('#all-agreements').prop('checked', true);
    } else {
        // 하나라도 체크가 해제된 경우 all-agreements도 체크 해제
        $('#all-agreements').prop('checked', false);
    }
});