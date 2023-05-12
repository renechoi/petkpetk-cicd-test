$(function() {
    $('#apibtn').click(function (){
        $.ajax({
            url:'/cls.jq/kakaopay.cls',
            dataType : 'json',
            success : function(data) {
                alert(data);
            } ,
            error : function (error) {
                alert(error);
            }
        });
    });
});