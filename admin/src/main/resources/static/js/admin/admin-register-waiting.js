$(document).ready(function () {
    var modal = $('#adminModal');
    var csrfToken = $("meta[name='_csrf']").attr("content");
    var csrfHeader = $("meta[name='_csrf_header']").attr("content");

    // 테이블 row를 클릭했을 때, 해당 row의 ID값을 이용하여 정보를 조회하고 모달을 엽니다.
    $('#dataTable tbody').on('click', 'tr', function () {
        var url = $(this).data('url');
        console.log(url);
        $.get(url, function (data) {
            $('#adminId').text(data.id);
            $('#adminName').text(data.name);
            $('#adminEmail').text(data.email);
            $('#adminRoles').text(data.roles.join(", "));
            $('#adminCreatedAt').text(data.createdAt);
            $('#adminApproved').text(data.approved);

            $('#approvalButton').off('click').on('click', function () {
                $.ajax({
                    url: url,
                    type: 'POST',
                    beforeSend: function (xhr) {
                        xhr.setRequestHeader(csrfHeader, csrfToken);

                    },
                    success: function () {
                        alert('승인 완료');
                        modal.modal('hide');
                        window.location.reload();
                    },
                    error: function () {
                        alert('승인 실패');
                    }
                });
            });


            modal.modal('show');
        });
    });
});

