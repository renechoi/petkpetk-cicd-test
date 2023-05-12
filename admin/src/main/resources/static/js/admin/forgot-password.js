function checkEmailExist() {
    let email = $("#exampleInputEmail").val();
    let csrfToken = $('meta[name="_csrf"]').attr("content");
    let csrfHeader = $('meta[name="_csrf_header"]').attr("content");
    let headers = {};
    headers[csrfHeader] = csrfToken;

    $.ajax({
        type: "POST",
        url: "/forgot-password",
        headers: headers,
        data: {email: email},
        success: function (result) {
            if (result) {
                $("#reset-password-button").hide();
                $('#email-check-success').html('이메일이 확인되었습니다.').css('color', 'green');
                $("#previous-password-form").show();
                $(".new-password-forms").show();
            } else {
                console.log("이메일이 존재합니다.");
            }
        },
        error: function (xhr, status, error) {
            alert("에러 발생");
        },
        complete: function () {
        },
    });
}


function resetPassword(event) {
    let email = $("#exampleInputEmail").val();
    let previousPassword = $("#previous-password-form").val();
    let newPassword = $("#new-password-form1").val();
    let csrfToken = $('meta[name="_csrf"]').attr("content");
    let csrfHeader = $('meta[name="_csrf_header"]').attr("content");
    let headers = {};
    headers[csrfHeader] = csrfToken;

    $.ajax({
        type: "POST",
        url: "/new-password",
        headers: headers,
        data: {email: email, previousPassword: previousPassword, newPassword: newPassword},
        success: function (result) {
            if (result) {
                alert("비밀번호가 변경되었습니다. 다시 로그인해주세요.");
                window.location.replace("/logout");
            } else {
            }
        },
        error: function (xhr, status, error) {
            alert("비밀번호를 다시 확인해주세요");
        },
        complete: function () {
        },
    });
}


$(document).ready(function () {
// 비밀번호와 확인 비밀번호가 일치하는지 검증하는 함수
    $("#new-password-form2").on("keyup", function () {
        if ($('#new-password-form2').val() === $('#previous-password-form').val()) {
            $('#password-identification').html('기존 비밀번호와 동일합니다.').css('color', 'green');
            $("#reset-password-button").prop('disabled', true);
            return;
        }
        if ($('#new-password-form1').val() === $('#new-password-form2').val()) {
            $('#password-identification').html('새로운 비밀번호가 일치합니다.').css('color', 'green');
            $("#reset-submit-button").prop('disabled', false);
        } else {
            $('#password-identification').html('새로운 비밀번호가 일치하지 않습니다.').css('color', 'red');
            $("#reset-password-button").prop('disabled', true);
            return;
        }

    });
});
