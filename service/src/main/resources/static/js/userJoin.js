function checkSameWithPass() {
    var password = document.getElementById("password");
    var re_password = document.getElementById("re-password");
    var check_password = document.getElementById("check-password");

    if (re_password.value.length == 0) {
        check_password.innerHTML = "";
    } else {

        if (password.value === re_password.value) {
            check_password.innerHTML = "";
        } else {
            check_password.innerHTML = "비밀번호가 일치하지 않습니다.<br>password does not match.";
        }

    }
}

function checkPass(e) {
    var check_password = document.getElementById("check-password");
    var re_password = document.getElementById("re-password");

    if (e.value == 0) {
        check_password.innerHTML = "";
    } else if (e.value === re_password.value) {
        check_password.innerHTML = "";
    } else if (re_password.value == "" || re_password.value == null) {
        check_password.innerHTML = "";
    } else {
        check_password.innerHTML = "비밀번호가 일치하지 않습니다.<br>password does not match.";
    }
}

function checkPhoneNumber() {
    var phoneNumber = document.getElementById("phoneNumber");

    if (phoneNumber.value.length > 12) {
        phoneNumber.value = phoneNumber.value.slice(0, 12);
    }

}

var errorMessage = document.getElementById("errorMessage");

var JoinForm = document.getElementById("JoinForm");
JoinForm.addEventListener("submit", function (event) {
    event.preventDefault();

    var email = document.getElementById("email");
    var name = document.getElementById("name");
    var nickName = document.getElementById("nickName");
    var password = document.getElementById("password");
    var detailAddress = document.getElementById("detailAddress");
    var re_password = document.getElementById("re-password");


    if ($("#name").val() == "") {
        errorMessage.innerHTML = "이름을 입력해주세요.";
        errorMessage.scrollIntoView({behavior: "smooth", block: "start"});
        name.focus();
        return false;
    }

    if ($("#password").val() == "") {
        errorMessage.innerHTML = "비밀번호를 입력해주세요.";
        errorMessage.scrollIntoView({behavior: "smooth", block: "start"});
        password.focus();
        return false;
    }
    if ($("#zipCode").val() == "") {
        errorMessage.innerHTML = "주소를 입력해주세요.";
        errorMessage.scrollIntoView({behavior: "smooth", block: "start"});
        return false;
    }
    if ($("#detailAddress").val() == "") {
        errorMessage.innerHTML = "상세주소를 입력해주세요.";
        errorMessage.scrollIntoView({behavior: "smooth", block: "start"});
        detailAddress.focus();
        return false;
    }

    if (password.value.length < 8 || password.value.length > 16) {
        errorMessage.innerHTML = "비밀번호는 8자 이상 16자 이하입니다.";
        errorMessage.scrollIntoView({behavior: "smooth", block: "start"});
        password.focus();
        return false;
    }

    if ($("#password").val() != $("#re-password").val() && $("#password").val() != "") {
        errorMessage.innerHTML = "비밀번호가 일치하지 않습니다.";
        errorMessage.scrollIntoView({behavior: "smooth", block: "start"});
        re_password.focus();
        return false;
    }

    if ($("#checkEmail").val() == "") {
        alert("이메일을 확인해주세요.");
        email.scrollIntoView({behavior: "smooth", block: "start"});
        return false;
    }

    if ($("#checkNickName").val() == "") {
        alert("닉네임을 확인해주세요.");
        nickName.scrollIntoView({behavior: "smooth", block: "start"});
        return false;
    }

    JoinForm.submit();

});


$("#email").on("keyup", function () {
    var checkEmailTxt = document.getElementById("checkEmailTxt");
    checkEmailTxt.textContent = "";
    $("#checkEmail").val("");
});
$("#nickName").on("keyup", function () {
    var checkNickTxt = document.getElementById("checkNickTxt");
    checkNickTxt.textContent = "";
    $("#checkNickName").val("");
});


function checkNewEmail() {
    if ($("#email").val().indexOf("@") === -1) {
        alert("이메일 형식으로 입력해주세요.");
        $("#checkEmail").val("");
        return false;
    }

    var checkEmailTxt = document.getElementById("checkEmailTxt");
    if ($("#email").val() == "") {
        checkNickTxt.textContent = "";
        $("#checkEmail").val("");
    }else{
    $.ajax({
        url: "/api/checkEmail",
        type: "post",
        data: {
            email: $("#email").val()
        },
        dataType: "json",
        success: function (sameEmail) {
            console.log(sameEmail);
            if (sameEmail == true) {
                checkEmailTxt.textContent = "중복 된 이메일입니다.";
                checkEmailTxt.style.color = "#ff3b57";
                $("#checkEmail").val("");
            } else {
                checkEmailTxt.textContent = "사용 가능한 이메일입니다.";
                checkEmailTxt.style.color = "rgb(185 146 200)";
                $("#checkEmail").val(1);
            }
        }

    })}
}

function checkNick() {
    var checkNickTxt = document.getElementById("checkNickTxt");
    if ($("#nickName").val() == "") {
        checkNickTxt.textContent = "";
        $("#checkNickName").val("");
    } else {
    $.ajax({
        url: "/api/checkNickName",
        type: "post",
        data: {
            nickname: $("#nickName").val(),
        },
        dataType: "json",
        success: function (sameNick) {
            if (sameNick == true) {
                checkNickTxt.textContent = "중복 된 닉네임입니다.";
                checkNickTxt.style.color = "#ff3b57";
                $("#checkNickName").val("");
            } else {
                checkNickTxt.textContent = "사용 가능한 닉네임입니다.";
                checkNickTxt.style.color = "rgb(185 146 200)";
                $("#checkNickName").val(1);
            }
        }
    })}
}

var profile;
$("#file").on('change',function(e){
    var file = e.target.files;
    var fileArr = Array.prototype.slice.call(file);
    fileArr.forEach(function (a) {
        profile = a;
        var reader = new FileReader();

        reader.onload = function (z) {
            $("#signupProfileImg").attr("src", z.target.result);
        };

        reader.readAsDataURL(a);

    });

    var sign_upProfileImg = document.getElementById("signupProfileImg");
    sign_upProfileImg.style.display = "block";

});

function togglePass() {
    var PassToggle = document.getElementById("PassToggle");
    var newPassword = document.getElementById("password");

    if (newPassword.getAttribute("type") == "password") {
        newPassword.setAttribute("type", "text");
        PassToggle.setAttribute("src", "/images/passwordHide.png")
    } else {
        newPassword.setAttribute("type", "password");
        PassToggle.setAttribute("src", "/images/passwordShow.png")
    }
}


function toggleRePass() {
    var rePassToggle = document.getElementById("rePassToggle");
    var reNewPassword = document.getElementById("re-password");

    if (reNewPassword.getAttribute("type") == "password") {
        reNewPassword.setAttribute("type", "text");
        rePassToggle.setAttribute("src", "/images/passwordHide.png")
    } else {
        reNewPassword.setAttribute("type", "password");
        rePassToggle.setAttribute("src", "/images/passwordShow.png")
    }
}