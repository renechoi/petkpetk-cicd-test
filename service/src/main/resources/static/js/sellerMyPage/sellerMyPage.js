if ($("img.userProfile").attr("src") != $("#userProfileImage").attr("src")) {
    $("img.userProfile").attr("src", $("#userProfileImage").attr("src"));

}

if ($("#userProfileImage").attr("src") != "/images/defaultProfile.png") {
    $.ajax({
        url:"/api/setUserPrincipalProfile",
        success: function () {
        }
    })
}

function openNewInfoBox() {
    var originalInformation = document.getElementById("originalInformation");
    var newUserInformationForm = document.getElementById("newUserInformationForm");

    originalInformation.style.display = "none";
    newUserInformationForm.style.display = "block";

    $("#checkNickName").val(1);
}

function hideNewInfoBox() {
    var originalInformation = document.getElementById("originalInformation");
    var newUserInformationForm = document.getElementById("newUserInformationForm");
    var checkNickTxt = document.getElementById("checkNickTxt");

    checkNickTxt.textContent = "";
    originalInformation.style.display = "block";
    newUserInformationForm.style.display = "none";

    $("#checkNickName").val("");
    $("#newName").val($("#name").text());
    $("#newNickName").val($("#nickName").text());
    $("#newBusinessName").val($("#businessName").text());
    $("#newPhoneNumber").val($("#phoneNumber").text());
    $("#zipcode").val($("#zip").val());
    $("#address1").val($("#addr1").val());
    $("#address2").val($("#addr2").val());
    $("#addressEtc").val($("#addrEtc").val());

}

function openNewPassBox() {
    var modifyPass = document.getElementById("modifyPass");
    var modifyInfo = document.getElementById("modifyInfo");
    var newPasswordForm = document.getElementById("newPasswordForm");

    modifyPass.style.display = "none";
    modifyInfo.style.display = "none";
    newPasswordForm.style.display = "block";



}

function hideNewPassBox() {
    var modifyPass = document.getElementById("modifyPass");
    var modifyInfo = document.getElementById("modifyInfo");
    var newPasswordForm = document.getElementById("newPasswordForm");
    var newPassword = document.getElementById("newPassword");
    var reNewPassword = document.getElementById("reNewPassword");


    modifyPass.style.display = "";
    modifyInfo.style.display = "";
    newPassword.value = "";
    reNewPassword.value = "";
    newPasswordForm.style.display = "none";


}


function togglePass() {
    var PassToggle = document.getElementById("PassToggle");
    var newPassword = document.getElementById("newPassword");

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
    var reNewPassword = document.getElementById("reNewPassword");

    if (reNewPassword.getAttribute("type") == "password") {
        reNewPassword.setAttribute("type", "text");
        rePassToggle.setAttribute("src", "/images/passwordHide.png")
    } else {
        reNewPassword.setAttribute("type", "password");
        rePassToggle.setAttribute("src", "/images/passwordShow.png")
    }
}

var newInfoForm = document.getElementById("newInfoForm");

if (newInfoForm) {
    newInfoForm.addEventListener('submit', function (event) {
        event.preventDefault();

        var name = document.querySelector("#newName");
        var nickName = document.querySelector("#newNickName");
        var zipcode = document.querySelector("#zipcode");
        var address2 = document.querySelector("#address2");
        var newBusinessName = document.querySelector("#newBusinessName");
        var newPhoneNumber = document.querySelector("#newPhoneNumber");

        if ($("#newName").val() == "") {
            alert("이름을 입력해주세요.");
            name.scrollIntoView({behavior: "smooth", block: "start"});
            name.focus();
            return false;
        }
        if ($("#newNickName").val() == "") {
            alert("닉네임을 입력해주세요.");
            nickName.scrollIntoView({behavior: "smooth", block: "start"});
            nickName.focus();
            return false;
        }
        if ($("#newBusinessName").val() == "") {
            alert("상호명을 입력해주세요.");
            newBusinessName.scrollIntoView({behavior: "smooth", block: "start"});
            newBusinessName.focus();
            return false;
        }
        if ($("#newPhoneNumber").val() == "") {
            alert("전화번호를 입력해주세요.");
            newPhoneNumber.scrollIntoView({behavior: "smooth", block: "start"});
            newPhoneNumber.focus();
            return false;
        }
        if ($("#zipcode").val() == "") {
            alert("주소를 검색해주세요.");
            zipcode.scrollIntoView({behavior: "smooth", block: "start"});
            zipcode.focus();
            return false;
        }
        if ($("#address2").val() == "") {
            alert("상세주소를 입력해주세요.");
            address2.scrollIntoView({behavior: "smooth", block: "start"});
            address2.focus();
            return false;
        }

        if ($("#checkNickName").val() == "") {
            alert("닉네임을 확인해주세요.");
            return false;
        }

        newInfoForm.submit();
    });
}

$("#newNickName").on("keyup", function () {
    var checkNickTxt = document.getElementById("checkNickTxt");
        checkNickTxt.textContent = "";
    $("#checkNickName").val("");

    if ($("#newNickName").val() == $("#nickName").text()) {
        $("#checkNickName").val(1);

    }
});

var newPassForm = document.getElementById("newPassForm");

newPassForm.addEventListener('submit', function (event) {
    event.preventDefault();

    var newPassword = document.getElementById("newPassword");
    var reNewPassword = document.getElementById("reNewPassword");

    if (newPassword.value == null || newPassword.value == "") {
        alert("새 비밀번호를 입력해주세요.");
        newPassword.focus();
        return false;
    }

    if (reNewPassword.value != newPassword.value) {
        alert("비밀번호가 일치하지 않습니다.");
        reNewPassword.focus();
        return false;
    }

    $.ajax({
        url: "/api/checkPass",
        type: "post",
        data: {
            password: newPassword.value,
            email: $("#userEmail").val()
        },
        dataType: "json",
        success: function (result) {
            if (result == true) {
                alert("기존 비밀번호와 동일합니다.");
                return false;
            }
            if (result == false) {
                // 기존 비밀번호와 다른 경우
                newPassForm.submit(); // form submit
            }
        }
    });
});


function checkNick() {
    var checkNickTxt = document.getElementById("checkNickTxt");
    if ($("#newNickName").val() == "") {
        checkNickTxt.textContent = "";
        $("#checkNickName").val("");
    } else {
    $.ajax({
        url: "/api/checkNickName",
        type: "post",
        data : {
            nickname : $("#newNickName").val(),
            email: $("#userEmail").val()
        },
        dataType: "json",
        success: function (sameNick) {
            console.log(sameNick);
            if (sameNick == true) {
                checkNickTxt.textContent = "중복 된 닉네임입니다.";
                checkNickTxt.style.color = "#ff3b57";
                $("#checkNickName").val("");
            } else {

                if ($("#newNickName").val() == $("#nickName").text()) {
                    checkNickTxt.textContent = "";
                    $("#checkNickName").val(1);

                } else {
                    checkNickTxt.textContent = "사용 가능한 닉네임입니다.";
                    checkNickTxt.style.color = "#9a9f73";
                    $("#checkNickName").val(1);

                }
            }
        }

    })}
}

var profile;
$("#file").on('change', function(e) {
    var file = e.target.files;
    var fileArr = Array.prototype.slice.call(file);
    fileArr.forEach(function(a) {
        profile = a;
        var reader = new FileReader();

        reader.onload = function(z) {
            $("#userProfileImage").attr("src", z.target.result);
        };

        reader.readAsDataURL(a);
    });
});

function showNewProfileBox() {
    var newProfileBox = document.getElementById("newProfileBox");
    var changeProfileBtn = document.getElementById("changeProfileBtn");
    newProfileBox.style.display = "flex";
    changeProfileBtn.style.display = "none";
}

function cancelNewProfile() {
    var newProfileBox = document.getElementById("newProfileBox");
    var changeProfileBtn = document.getElementById("changeProfileBtn");
    var originalProfile = document.getElementById("originalProfile");
    var userProfileImage = document.getElementById("userProfileImage");
    $("#isProfileDeleted").val(false);

    userProfileImage.setAttribute("src", originalProfile.value);
    newProfileBox.style.display = "none";
    changeProfileBtn.style.display = "block";
}

function deleteProfile() {
    var userProfileImage = document.getElementById("userProfileImage");
    $("#isProfileDeleted").val(true);

    userProfileImage.setAttribute("src", "/images/defaultProfile.png");

}