$(document).ready(function () {
    var modal = $('#shoppingNoticeModal');
    var csrfToken = $("meta[name='_csrf']").attr("content");
    var csrfHeader = $("meta[name='_csrf_header']").attr("content");

    $('#dataTable tbody').on('click', 'tr', function () {
        var url = $(this).data('url');
        $.get(url, function (data) {
            $('#noticeIdModal').text($('#noticeId').text());
            $('#noticeTitleModal').text($('#noticeTitle').text());
            $('#noticeContentModal').text($('#noticeContent').text());
            $('#noticeCreatedAtModal').text($('#noticeCreatedAt').text());

            $('#deleteButton').off('click').on('click', function () {
                $.ajax({
                    url: url,
                    type: 'POST',
                    beforeSend: function (xhr) {
                        xhr.setRequestHeader(csrfHeader, csrfToken);

                    },
                    success: function () {
                        modal.modal('hide');
                        window.location.reload();
                    },
                    error: function () {
                        alert('삭제 실패');
                    }
                });
            });



            modal.modal('show');
        });
    });
});

