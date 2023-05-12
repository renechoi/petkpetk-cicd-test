var commentForm = document.getElementById("commentForm");

if (commentForm) {
    commentForm.addEventListener("submit", function (event) {
        event.preventDefault();
        $("#articleId").val($(".articleId").val());
        $("#userEmail").val($(".userEmail").val());

        var commentContents = document.getElementById("commentContents");

        if ($(".commentContents").val() == "") {
            alert("댓글 내용을 입력해주세요.");
            commentContents.focus();
            return false;
        }

        commentForm.submit();
    });
}


function showModifyCommentBox(commentId) {
    var newCommentForm = document.getElementById("newCommentForm" + commentId);
    var comment = document.getElementById("comment"+commentId);
    var newComment = document.getElementById("newComment"+commentId);
    var commentBtns = document.getElementById("commentBtns" + commentId);
    var cancelOrCompleteBtns = document.getElementById("cancelOrCompleteBtns" + commentId);
    var commentBigBtns = document.getElementsByClassName("commentBigBtns");
    var childCommentBtns = document.getElementsByClassName("childCommentBtns");
    var newBigCommentBtns = document.getElementsByClassName("newBigCommentBtn");

    for (let i = 0; i < commentBigBtns.length; i++) {
        commentBigBtns[i].style.display = "none";
    }
    for (let i = 0; i < childCommentBtns.length; i++) {
        childCommentBtns[i].style.display = "none";
    }
    for (let i = 0; i < newBigCommentBtns.length; i++) {
        newBigCommentBtns[i].style.display = "none";
    }

    newCommentForm.style.display = "block";
    cancelOrCompleteBtns.style.display = "flex";
    comment.style.display = "none";
    commentBtns.style.display = "none";

    newComment.value = comment.textContent;
    newComment.focus();
}

function cancelModifyComment(commentId) {
    var newCommentForm = document.getElementById("newCommentForm" + commentId);
    var comment = document.getElementById("comment"+commentId);
    var newComment = document.getElementById("newComment"+commentId);
    var commentBtns = document.getElementById("commentBtns" + commentId);
    var cancelOrCompleteBtns = document.getElementById("cancelOrCompleteBtns" + commentId);
    var commentBigBtns = document.getElementsByClassName("commentBigBtns");
    var childCommentBtns = document.getElementsByClassName("childCommentBtns");
    var newBigCommentBtns = document.getElementsByClassName("newBigCommentBtn");

    for (let i = 0; i < newBigCommentBtns.length; i++) {
        newBigCommentBtns[i].style.display = "block";
    }
    for (let i = 0; i < childCommentBtns.length; i++) {
        childCommentBtns.item(i).style.display = "block";
    }
    for (let i = 0; i < commentBigBtns.length; i++) {
        commentBigBtns[i].style.display = "flex";
    }

    newComment.value = comment.textContent;

    newCommentForm.style.display = "none";
    cancelOrCompleteBtns.style.display = "none";
    comment.style.display = "block";
    commentBtns.style.display = "flex";
}

function submitModifyComment(commentId) {
    var newCommentForm = document.getElementById("newCommentForm" + commentId);
    var newComment = document.getElementById("newComment"+commentId);

    if (newComment.value == "") {
        alert("수정 할 댓글 내용을 적어주세요.")
        newComment.focus();
        return false;
    }
    newCommentForm.submit();
}

function showNewChildCommentBox(commentId, childCommentId) {
    var newBigCommentBtns = document.getElementsByClassName("newBigCommentBtn");
    var newChildCommentForm = document.getElementById("newChildCommentForm" + commentId);
    var childCommentBtns = document.getElementsByClassName("childCommentBtns");
    var commentBigBtns = document.getElementsByClassName("commentBigBtns");

    newChildCommentForm.style.display = "block";

    for (let i = 0; i < newBigCommentBtns.length; i++) {
        newBigCommentBtns[i].style.display = "none";
    }

    for (let i = 0; i < childCommentBtns.length; i++) {
        childCommentBtns[i].style.display = "none";
    }
    for (let i = 0; i < commentBigBtns.length; i++) {
        commentBigBtns[i].style.display = "none";
    }

    if (childCommentId != null) {
        $("#newChildCommentContent" + commentId).val('@'+$("#CommentUserNickName" + childCommentId).val()+' ');
    }

    $("#newChildCommentParentId" + commentId).val(commentId);
    $("#newChildCommentUserEmail"+commentId).val($(".userEmail").val());
    $("#newChildCommentArticleId"+commentId).val($(".articleId").val());
}

function cancelNewComment(commentId) {
    var newBigCommentBtns = document.getElementsByClassName("newBigCommentBtn");
    var newChildCommentForm = document.getElementById("newChildCommentForm" + commentId);
    var childCommentBtns = document.getElementsByClassName("childCommentBtns");
    var commentBigBtns = document.getElementsByClassName("commentBigBtns");

    newChildCommentForm.style.display = "none";

    for (let i = 0; i < newBigCommentBtns.length; i++) {
        newBigCommentBtns[i].style.display = "block";
    }
    for (let i = 0; i < childCommentBtns.length; i++) {
        childCommentBtns.item(i).style.display = "block";
    }
    for (let i = 0; i < commentBigBtns.length; i++) {
        commentBigBtns[i].style.display = "flex";
    }

    $('#newChildCommentContent' + commentId).val("");
}

function showModifyChildCommentBox(childCommentId) {
    var childComment = document.getElementById("childComment" + childCommentId);
    var modifyChildCommentForm = document.getElementById("modifyChildCommentForm" + childCommentId);
    var anotherChildCommentBtns = document.getElementById("anotherChildCommentBtns" + childCommentId);
    var commentBigBtns = document.getElementsByClassName("commentBigBtns");
    var newBigCommentBtns = document.getElementsByClassName("newBigCommentBtn");
    var childCommentBtns = document.getElementsByClassName("childCommentBtns");

    for (let i = 0; i < newBigCommentBtns.length; i++) {
        newBigCommentBtns[i].style.display = "none";
    }
    for (let i = 0; i < commentBigBtns.length; i++) {
        commentBigBtns[i].style.display = "none";
    }
    for (let i = 0; i < childCommentBtns.length; i++) {
        childCommentBtns[i].style.display = "none";
    }

    $("#modifyChildComment" + childCommentId).val($("#childComment"+childCommentId).text());

    childComment.style.display = "none";
    modifyChildCommentForm.style.display = "block";
    anotherChildCommentBtns.style.display = "flex";

}

function cancelModifyChildComment(childCommentId) {
    var childComment = document.getElementById("childComment" + childCommentId);
    var modifyChildCommentForm = document.getElementById("modifyChildCommentForm" + childCommentId);
    var anotherChildCommentBtns = document.getElementById("anotherChildCommentBtns" + childCommentId);
    var commentBigBtns = document.getElementsByClassName("commentBigBtns");
    var newBigCommentBtns = document.getElementsByClassName("newBigCommentBtn");
    var childCommentBtns = document.getElementsByClassName("childCommentBtns");

    for (let i = 0; i < newBigCommentBtns.length; i++) {
        newBigCommentBtns[i].style.display = "block";
    }
    for (let i = 0; i < commentBigBtns.length; i++) {
        commentBigBtns[i].style.display = "flex";
    }
    for (let i = 0; i < childCommentBtns.length; i++) {
        childCommentBtns[i].style.display = "block";
    }
    $("#modifyChildComment" + childCommentId).val("");

    childComment.style.display = "block";
    modifyChildCommentForm.style.display = "none";
    anotherChildCommentBtns.style.display = "none";
}


function submitModifyChildComment(childCommentId) {
    var modifyChildCommentForm = document.getElementById("modifyChildCommentForm" + childCommentId);
    var modifyChildComment = document.getElementById("modifyChildComment" + childCommentId);

    if ($("#modifyChildComment"+childCommentId).val() == "") {
        alert("댓글 내용을 입력해주세요.");
        modifyChildComment.focus();
        return false;
    }

    modifyChildCommentForm.submit();
}

