$(document).ready(function () {
    var modal = $('#qnaModal');
    var csrfToken = $("meta[name='_csrf']").attr("content");
    var csrfHeader = $("meta[name='_csrf_header']").attr("content");

    $('#dataTable tbody').on('click', 'tr', function () {
        var url = $(this).data('url');
        $.get(url, function (data) {
            $('#qnaIdModal').text($('#qnaId').text());
            $('#qnaCategoryModal').text($('#qnaCategory').text());
            $('#qnaContentModal').text($('#qnaContent').text());
            $('#qnaPhoneNumberModal').text($('#qnaPhoneNumber').text());
            $('#qnaCreatedAtModal').text($('#qnaCreatedAt').text());
            $('#qnaAnswerStatusModal').text($('#qnaAnswerStatus').text());

            $('#answerButton').off('click').on('click', function () {
                let answer = $('#answerText').val();
                $.ajax({
                    url: '/management/qna/register',
                    type: 'POST',
                    data: {"answer": answer, "userAskId":$('#qnaId').text()},
                    beforeSend: function (xhr) {
                        xhr.setRequestHeader(csrfHeader, csrfToken);
                    },
                    success: function () {
                        modal.modal('hide');
                        window.location.reload();
                    },
                    error: function () {
                        alert('답변 실패');
                    }
                });
            });

            modal.modal('show');
        });
    });
});

function filterRows() {
    let tableRows = document.querySelectorAll("#dataTable tbody tr");
    let selectedAnswerStatus = document.querySelector('input[name="answerStatus"]:checked').value;

    for (let i = 0; i < tableRows.length; i++) {
        let rowAnswerStatus = tableRows[i].querySelector("#qnaAnswerStatus").innerText;

        if (selectedAnswerStatus === "answered" && rowAnswerStatus !== "ANSWERED") {
            tableRows[i].style.display = "none";
        } else if (selectedAnswerStatus === "answering" && rowAnswerStatus !== "ANSWERING") {
            tableRows[i].style.display = "none";
        } else {
            tableRows[i].style.display = "";
        }
    }
}



