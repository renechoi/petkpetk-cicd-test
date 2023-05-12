$(document).ready(function () {
    var modal = $('#articleModal');
    var csrfToken = $("meta[name='_csrf']").attr("content");
    var csrfHeader = $("meta[name='_csrf_header']").attr("content");

    $('#dataTable tbody').on('click', 'tr', function () {
        var url = $(this).data('url');
        $.get(url, function (data) {
            $('#articleId').text(data.id);
            $('#name').text(data.name);
            $('#email').text(data.email);
            $('#articleTitleModal').text($('#articleTitle').text());
            $('#articleContentModal').text($('#articleContent').text());
            $('#articleHitModal').text($('#articleHit').text());
            $('#categoryTypeModal').text($('#articleCategoryType').text());
            $('#createdAt').text(data.createdAt);

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

