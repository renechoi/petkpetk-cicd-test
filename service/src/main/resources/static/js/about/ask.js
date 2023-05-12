$("#myAskBtn").on("click", function () {
    $("#askHistoryBox").css("display", "block");
    $("#newAskBox").css("display", "none");

    $("#myAskBtn").css("border-bottom", "2px solid #FF5F75");
    $("#myAskBtn").css("color", "#ff4c65");
    $("#myAskBtn").css("fontWeight", "bold");

    $("#newAskBtn").css("border-bottom", "2px solid #ffc9d1");
    $("#newAskBtn").css("color", "rgb(192 149 155)");
    $("#newAskBtn").css("fontWeight", "normal");
});

$("#newAskBtn").on("click", function () {
    $("#newAskBox").css("display", "block");
    $("#askHistoryBox").css("display", "none");

    $("#myAskBtn").css("border-bottom", "2px solid #ffc9d1");
    $("#myAskBtn").css("color", "rgb(192 149 155)");
    $("#myAskBtn").css("fontWeight", "normal");

    $("#newAskBtn").css("border-bottom", "2px solid #FF5F75");
    $("#newAskBtn").css("color", "#ff4c65");
    $("#newAskBtn").css("fontWeight", "bold");
});

var newAskForm = document.getElementById("newAskForm");

newAskForm.addEventListener("submit", function (event) {
    event.preventDefault();

    if ($("#QnACategory").val()=="") {
        var QnACategory = document.getElementById("QnACategory");
        alert("문의 유형을 선택해주세요.");
        QnACategory.scrollIntoView({behavior: "smooth", block: "start"});
        return false;
    }

    if ($("#askContent").val()=="") {
        var askContent = document.getElementById("askContent");
        alert("문의 내용을 10자 이상 적어주세요.");
        askContent.scrollIntoView({behavior: "smooth", block: "start"});
        return false;
    }

    if (!$("#agree").prop("checked")) {
        $("#agreeText").val("비동의");
    } else {
        $("#agreeText").val("동의");
    }

    newAskForm.submit();
});

function checkPhoneNumber() {
    var phoneNumber = document.getElementById("phoneNumber");

    if (phoneNumber.value.length > 13) {
        phoneNumber.value = phoneNumber.value.slice(0, 13);
    }

}

$("#QnACategory").add($("#askContent")).on("input", function() {

    var value1 = $("#QnACategory").val().trim();
    var value2 = $("#askContent").val().trim();

    // 두 입력 필드의 값이 모두 존재하면 div 요소를 블록 상태로 변경합니다.
    if (value1 && value2) {
        $("#agreeBox").css("display", "block");
    } else {
        $("#agreeBox").css("display", "none");
    }
});

$("#showPolicy").on("click", function () {
    $("#askPolicyBox").css("display", "block");
});
$("#closeAskPolicy").on("click", function () {
    $("#askPolicyBox").css("display", "none");
});


var createAtList = document.getElementsByClassName("createdAt");

for (let i = 0; i < createAtList.length; i++) {
    const askDateTime = moment(createAtList.item(i).value);
    const askDays = moment().diff(askDateTime, 'days');

    let dateDiffText = '';

    if (askDays === 0) {
        // 오늘 날짜인 경우
        dateDiffText = '오늘';
    } else if (askDays === 1) {
        // 1일 전인 경우
        dateDiffText = '1일 전';
    } else if (askDays <= 3) {
        // 2일 전 ~ 3일 전인 경우
        dateDiffText = `${askDays}일 전`;
    } else if (askDays < 365) {
        // 1년 이내인 경우
        dateDiffText = `${askDateTime.format('MM월 D일')}`;
    } else {
        // 1년 이상인 경우
        dateDiffText = `${askDateTime.format('YYYY년 M월 DD일')}`;
    }

    // 텍스트를 div에 출력
    $('#askCreatedAt'+i).text(dateDiffText);

}


function openAsk(askId) {

}

function changeArrow(askId) {

    if (!$(".ask" + askId).hasClass("openAsk")) {
        $(".ask" + askId).addClass("openAsk");
        $(".askContent" + askId).removeClass("hideOverFlow");
    } else {
        $(".ask" + askId).removeClass("openAsk");
        $(".askContent" + askId).addClass("hideOverFlow");
    }

}