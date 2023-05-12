function checkSameWithPass(){
    var password = document.getElementById("password");
    var re_password = document.getElementById("re-password");
    var check_password = document.getElementById("check-password");


    if (re_password.value.length == 0) {
        check_password.innerHTML = "";
    }else{

        if (password.value === re_password.value) {
            check_password.innerHTML = "";
        }else{
            check_password.innerHTML = "비밀번호가 일치하지 않습니다.<br>Password does not match.";
        }

    }

}

function checkPassBlank(){

}
function addAddrDetail(){
    var address = document.getElementById("address");
    var zipcode = document.getElementById("sample6_postcode");
    var  addr = document.getElementById("sample6_address");
    var detailAddr = document.getElementById("sample6_detailAddress");
    var addretc = document.getElementById("sample6_extraAddress");

    address.value ="( "+ zipcode.value +" )"+" "+ addr.value + " " + detailAddr.value + " " + addretc.value;
    console.log(address.value);
}