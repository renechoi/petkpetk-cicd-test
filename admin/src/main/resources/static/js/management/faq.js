function registerCategory() {
    let modal = $('#faqCategoryRegisterModal');
    let csrfToken = $("meta[name='_csrf']").attr("content");
    let csrfHeader = $("meta[name='_csrf_header']").attr("content");
    let url = '/management/faq/category/register';
    modal.modal('show');

    $('#saveButton').off('click').on('click', function () {
        $.ajax({
            url: url,
            type: 'POST',
            data: {"name": $('#faqCategoryName').val(), "description": $('#faqCategoryDescription').val()},
            beforeSend: function (xhr) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
            success: function () {
                modal.modal('hide');
                window.location.reload();
            },
            error: function () {
                alert('등록 실패');
            }
        });
    });
}