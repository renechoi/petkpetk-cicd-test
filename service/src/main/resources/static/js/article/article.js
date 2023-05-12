const imagesPreview = document.createElement('div');
imagesPreview.classList.add('images-preview');

const imagesInput = document.getElementById('images');
imagesInput.parentNode.insertBefore(imagesPreview, imagesInput.nextSibling);

imagesInput.addEventListener('change', function() {
    imagesPreview.innerHTML = ''; // Clear previous preview
    for (let i = 0; i < this.files.length; i++) {
        const file = this.files[i];
        if (file.type.startsWith('image/')) {
            const reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = function() {
                const img = document.createElement('img');
                img.src = reader.result;
                img.classList.add("articleImages");
                imagesPreview.appendChild(img);
            }
        }
    }
});

const hashtagInput = document.getElementById('hashtag');
hashtagInput.addEventListener('focus', addHashtagSymbol);
hashtagInput.addEventListener('keydown', handleHashtagInput);

function addHashtagSymbol() {
    if (!hashtagInput.value.startsWith('#')) {
        hashtagInput.value = '#' + hashtagInput.value;
    }
}

function handleHashtagInput(event) {
    const message = hashtagInput.parentNode.querySelector('.error-message');
    if (isInValidKey(event.key)) {
        if (!message) {
            let newMessage = "";
            $("#error-hashtag").addClass('error-message');
            newMessage = '해시태그에 특수문자는 입력할 수 없습니다.';
            $("#error-hashtag").css("color", "#ff7f7f");
            $("#error-hashtag").text(newMessage);
            event.preventDefault();
        }
        event.preventDefault();
    } else if (event.keyCode === 32 ) {
            hashtagInput.value += ' #';
        event.preventDefault();
    } else if (event.key === '#' && hashtagInput.value.endsWith('#')) {
        let newMessage = "";
        $("#error-hashtag").addClass('error-message');
        newMessage = '해시태그는 중복할 수 없습니다.';
        $("#error-hashtag").css("color", "#ff7f7f");
        $("#error-hashtag").text(newMessage);
        event.preventDefault();
    } else if (event.key === '#' ) {
        hashtagInput.value += ' #';
        event.preventDefault();


    } else {
        if (message) {
            message.remove();
        }
    }
}

function isInValidKey(key) {
    const notAllowedKeys = ['Delete', 'ArrowUp', 'ArrowDown', 'Control', 'Alt', 'Enter'];
    return /^[!@$%^&*()_+,.\-=/<>?;':"|}{\[\]\\]+$/.test(key) || notAllowedKeys.includes(key);
}

var num = 1


function checkHashTag() {
    if ($("#hashtag").val()=="#") {
        $("#hashtag").val("");
    }
}

$("#countText").text($("#content").val().length+'/5000');

function countText() {
    $("#countText").text($("#content").val().length+'/5000');

    if ($("#content").val().length > 5000) {
        $("#content").css("background-color", "#ffebeb");
        $("#countText").css("color", "red");
    } else {
        $("#content").css("background-color", "white");
        $("#countText").css("color", "#856868");
    }
}

var articlePostForm = document.getElementById("articlePostForm");

articlePostForm.addEventListener("submit", function (event) {
    event.preventDefault();

    if ($("#content").val().length > 5000) {
        alert("내용은 5000자 이하로 입력해주세요.")
        return false;
    }

    articlePostForm.submit();
});