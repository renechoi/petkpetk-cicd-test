function showNoticeContent() {
    if ($(".noticeContent").css("display") == "none") {
        $(".noticeContent").css("display", "block")
        expandElement($(".noticeContent"), 600);
        $(".arrow").addClass("openNoticeContent");
    } else {
        collapseElement($(".noticeContent"), 600);
        $(".arrow").removeClass("openNoticeContent");
    }
}

function expandElement(element, second) {
        element.animate({
            height: element.get(0).scrollHeight,
            padding: "50px 20px"
        }, second);
}

function collapseElement(element, duration) {
    element.animate({
        height: "0px",
        padding: "0px 20px"
    }, duration, function() {
        element.css("display", "none");
    });
}