function openNewInfoBox() {
    var originalInformation = document.getElementById("originalInformation");
    var newUserInformationForm = document.getElementById("newUserInformationForm");

    originalInformation.style.display = "none";
    newUserInformationForm.style.display = "block";
}

function hideNewInfoBox() {
    var originalInformation = document.getElementById("originalInformation");
    var newUserInformationForm = document.getElementById("newUserInformationForm");

    originalInformation.style.display = "block";
    newUserInformationForm.style.display = "none";
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

    modifyPass.style.display = "";
    modifyInfo.style.display = "";
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

var newPassword = document.getElementById("newPassword");
var reNewPassword = document.getElementById("reNewPassword");

function checkPassword() {
    if (reNewPassword.value != newPassword.value) {
        alert("비밀번호가 일치하지 않습니다.")
        reNewPassword.focus();
    }
}

var newPassForm = document.getElementById("newPassForm");
newPassForm.addEventListener('submit', function (event) {

    if (reNewPassword.value != newPassword.value) {
        event.preventDefault();
        return false;
    }
});
