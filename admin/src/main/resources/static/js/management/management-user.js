$(document).ready(function () {
    var modal = $('#userModal');
    var csrfToken = $("meta[name='_csrf']").attr("content");
    var csrfHeader = $("meta[name='_csrf_header']").attr("content");

    // 테이블 row를 클릭했을 때, 해당 row의 ID값을 이용하여 정보를 조회하고 모달을 엽니다.
    $('#dataTable tbody').on('click', 'tr', function () {
        var url = $(this).data('url');
        $.get(url, function (data) {
            // 조회한 정보를 modal 창의 각 항목에 채워줍니다.
            $('#userId').text(data.id);
            $('#userName').text(data.name);
            $('#userEmail').text(data.email);
            $('#userRoles').text(data.roles.join(", "));
            $('#userBusinessName').text(data.businessName || '');
            $('#userBusinessNumber').text(data.businessNumber || '');
            $('#userCreatedAt').text(data.createdAt);

// 삭제 버튼 클릭 시 해당 계정을 삭제하는 이벤트를 등록합니다.
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

// 삭제 버튼을 disabled 처리합니다.
            if (data.roles.includes('ADMIN')) {
                $('#deleteButton').prop('disabled', true);
            }else {
                $('#deleteButton').prop('disabled', false);
            }



            // modal 창을 엽니다.
            modal.modal('show');
        });
    });
});

