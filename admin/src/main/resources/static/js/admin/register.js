


$(document).ready(function () {

    let timer;

    // name 필드가 비어 있는지 체크하는 함수
    function validateName() {
        if ($('#name-input').val() === '') {
            disableSubmitButton();
        } else {
            enableSubmitButton();
        }
    }

    // 비밀번호와 확인 비밀번호가 일치하는지 검증하는 함수
    function validatePassword() {
        if ($('#password').val() === $('#password-repeat').val()) {
            $('#password-identification').html('일치합니다.').css('color', 'green');
            enableSubmitButton();
        } else {
            $('#password-identification').html('일치하지 않습니다.').css('color', 'red');
            disableSubmitButton(); // 패스워드 일치하지 않을 경우에도 submit 버튼 비활성화
        }
    }

    function validateEmail(email) {
        const re = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
        return re.test(String(email).toLowerCase());
    }


    // 이메일 유효성 검증 및 중복 이메일 검증을 실행하는 함수
    $('input[name="email"]').on('blur', function () {
        let email = $(this).val();
        if (validateEmail(email)) {
            let csrfToken = $('meta[name="_csrf"]').attr('content');
            let csrfHeader = $('meta[name="_csrf_header"]').attr('content');
            let headers = {};
            headers[csrfHeader] = csrfToken;
            $.ajax({
                url: '/check-email',
                type: 'POST',
                headers: headers,
                data: {'email': email},
                success: function (data) {
                    if (data) {
                        $('#email-check-msg').html('사용 가능한 Email입니다.').css('color', 'green');
                        enableSubmitButton();
                    } else {
                        $('#email-check-msg').html('이미 사용 중인 Email입니다.').css('color', 'red');
                    }
                },
                error: function () {
                    alert('서버 오류로 인해 Email 중복 검사에 실패했습니다.');
                    enableSubmitButton();
                }
            });
        } else {
            $('#email-check-msg').html('올바른 이메일 형식이 아닙니다.').css('color', 'red');
        }
    });


    $('#send-auth-code-btn').on('click', function () {
        let email = $('#email-input').val();
        let csrfToken = $('meta[name="_csrf"]').attr('content');
        let csrfHeader = $('meta[name="_csrf_header"]').attr('content');
        let headers = {};
        headers[csrfHeader] = csrfToken;
        if (email) {
            $(this).prop('disabled', true);
            const loadingIcon = $('<div>', {class: 'loading-icon'});
            const spinnerIcon = $('<i>', {class: 'fa fa-spinner fa-spin'});
            loadingIcon.append(spinnerIcon);
            $(this).closest('.input-group-append').append(loadingIcon.css({
                'position': 'absolute',
                'top': '50%',
                'left': '50%',
                'transform': 'translate(-50%, -50%)'
            }));
            $.ajax({
                type: 'POST',
                url: '/send-verification-code',
                headers: headers,
                data: {email: email},
                success: function (result) {
                    $('.form-container').show();
                    startTimer(180, '.form-input-timer');
                },
                error: function (xhr, status, error) {
                    alert('에러 발생');
                },
                complete: function () {
                    loadingIcon.remove();
                }
            });
        } else {
            alert('이메일을 입력하세요.');
        }
    });


    // submit 버튼을 활성화하는 함수
    function enableSubmitButton() {
        if ($('#password-identification').html() === '일치합니다.'
            && $('#email-check-msg').html() === '사용 가능한 Email입니다.'
            && $('#name-input').val() !== ''
        ) {
            $('button[type="submit"]').prop('disabled', false);
        }
    }

    // submit 버튼을 비활성화하는 함수
    function disableSubmitButton() {
        $('button[type="submit"]').prop('disabled', true);
    }

    $('#name-input').on('keyup', function () {
        validateName();
        enableSubmitButton();
    });

    $('#password-repeat').on('keyup', function () {
        validatePassword();
        enableSubmitButton();
    });

    $('input[name="email"]').on('input', function () {
        $('#email-check-msg').text('');
        disableSubmitButton();
    });


    function startTimer(duration, selector) {
        timer = setInterval(function () {
            let minutes = Math.floor(duration / 60);
            let seconds = duration % 60;
            $(selector).text(minutes + ':' + (seconds < 10 ? '0' : '') + seconds);
            duration--;
            if (duration < 0) {
                clearInterval(timer);
                $(selector).text('시간 초과');
            }
        }, 1000);
    }

    // 인증코드 input box에 입력이 있을 때마다 실행되는 함수
    $('#auth-code-input').on('input', function() {
        if($(this).val().length === 6) {
            $('button.form-submit-button').prop('disabled', false);
            return;
        }
        $('button.form-submit-button').prop('disabled', true);

    });

    $('.form-submit-button').on('click', function () {
        let email = $('#email-input').val();
        let verificationCode = $('#auth-code-input').val();
        console.log(verificationCode);
        let csrfToken = $('meta[name="_csrf"]').attr('content');
        let csrfHeader = $('meta[name="_csrf_header"]').attr('content');
        let headers = {};
        headers[csrfHeader] = csrfToken;
        if (verificationCode) {
            $.ajax({
                type: 'POST',
                url: '/verify-verification-code',
                headers: headers,
                data: {email:email, verificationCode: verificationCode},
                success: function (result) {
                    if(result){
                        clearInterval(timer);
                        $('.form-input-timer').text('인증 완료');
                        $('#email-validation-form-text').html('이메일 인증이 완료되었습니다').css('color', 'green');
                    }else{
                        alert('인증번호가 일치하지 않습니다.');
                    }
                },
                error: function (xhr, status, error) {
                    alert('에러 발생');
                }
            });
        }
    });



});




