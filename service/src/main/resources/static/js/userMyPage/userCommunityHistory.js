$(".articleBtn").click(function () {
    $("#userArticleHistory").css("display", "block");
    $("#userCommentHistory").css("display", "none");

    $(".articleBtn").addClass("focusBtn");
    $(".commentBtn").removeClass("focusBtn");
});

$(".commentBtn").click(function () {
    $("#userArticleHistory").css("display", "none");
    $("#userCommentHistory").css("display", "block");

    $(".articleBtn").removeClass("focusBtn");
    $(".commentBtn").addClass("focusBtn");
});

function hoverArticleContent(articleId) {
    if ($(".hoverArticle" + articleId).hasClass("hoverClass")) {
        $(".hoverArticle" + articleId).removeClass("hoverClass");

    } else if(!$(".hoverArticle" + articleId).hasClass("hoverClass")){
        $(".hoverArticle" + articleId).addClass("hoverClass");
    }
}
function hoverCommentContent(commentId) {
    if ($(".hoverComment" + commentId).hasClass("hoverClass")) {
        $(".hoverComment" + commentId).removeClass("hoverClass");

    } else if(!$(".hoverComment" + commentId).hasClass("hoverClass")){
        $(".hoverComment" + commentId).addClass("hoverClass");
    }
}


var articleCreatedAtList = document.getElementsByClassName("articleCreatedAtList");
var commentModifiedAtList = document.getElementsByClassName("commentModifiedAtList");

for (let i = 0; i < articleCreatedAtList.length; i++) {

    const articleDateTime = moment(articleCreatedAtList.item(i).value);
    const articleMinutes = moment().diff(articleDateTime, 'minutes');
    const articleHours = moment().diff(articleDateTime, 'hours');
    const articleDays = moment().diff(articleDateTime, 'days');

    let dateDiffText = '';

    if (articleMinutes < 1) {
        // 1분 이내인 경우
        const articleSeconds = moment().diff(articleDateTime, 'seconds');
        dateDiffText = `${articleSeconds}초 전`;
    }else if (articleHours < 1) {
        // 1시간 이내인 경우
        const articleMinutes = moment().diff(articleDateTime, 'minutes');
        dateDiffText = `${articleMinutes}분 전`;
    } else if (articleDays < 1) {
        // 24시간 이내인 경우
        dateDiffText = `${articleHours}시간 전`;
    } else if (articleDays === 1) {
        // 1일 전인 경우
        dateDiffText = '1일 전';
    } else if (articleDays <= 3) {
        // 2일 전 ~ 3일 전인 경우
        dateDiffText = `${articleDays}일 전`;
    } else if (articleDays < 365) {
        // 1년 이내인 경우
        dateDiffText = `${articleDateTime.format('M월 D일')}`;
    } else {
        // 1년 이상인 경우
        dateDiffText = `${articleDateTime.format('YYYY년 M월 D일')}`;
    }

    $('#articleCreatedAt' + (i+1)).text(dateDiffText);
}


for (let i = 0; i < commentModifiedAtList.length; i++) {

    console.log(commentModifiedAtList.item(i).value);

    const commentDateTime = moment(commentModifiedAtList.item(i).value);
    const commentMinutes = moment().diff(commentDateTime, 'minutes');
    const commentHours = moment().diff(commentDateTime, 'hours');
    const commentDays = moment().diff(commentDateTime, 'days');

    let dateDiffText = '';

    if (commentMinutes < 1) {
        // 1분 이내인 경우
        const commentSeconds = moment().diff(commentDateTime, 'seconds');
        dateDiffText = `${commentSeconds}초 전`;
    }else if (commentHours < 1) {
        // 1시간 이내인 경우
        const commentMinutes = moment().diff(commentDateTime, 'minutes');
        dateDiffText = `${commentMinutes}분 전`;
    } else if (commentDays < 1) {
        // 24시간 이내인 경우
        dateDiffText = `${commentHours}시간 전`;
    } else if (commentDays === 1) {
        // 1일 전인 경우
        dateDiffText = '1일 전';
    } else if (commentDays <= 3) {
        // 2일 전 ~ 3일 전인 경우
        dateDiffText = `${commentDays}일 전`;
    } else if (commentDays < 365) {
        // 1년 이내인 경우
        dateDiffText = `${commentDateTime.format('M월 D일')}`;
    } else {
        // 1년 이상인 경우
        dateDiffText = `${commentDateTime.format('YYYY년 M월 D일')}`;
    }

    $('#commentModifiedAt' + (i+1)).text(dateDiffText);
}