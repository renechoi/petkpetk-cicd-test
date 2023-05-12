$(document).ready(function () {
    var modal = $('#itemModal');
    var csrfToken = $("meta[name='_csrf']").attr("content");
    var csrfHeader = $("meta[name='_csrf_header']").attr("content");

    $('#dataTable tbody').on('click', 'tr', function () {
        var url = $(this).data('url');
        $.get(url, function (data) {
            var itemId = data.id ? data.id : url.substring(url.indexOf("shopping-items") + "shopping-items".length + 1);
            $('#itemId').text(itemId);
            $('#itemName').text(data.itemName);
            $('#itemDetail').text(data.itemDetail);
            $('#price').text(data.price);
            $('#itemStatus').text(data.itemStatus);

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




