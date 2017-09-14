define(['ajaxPackage', 'messageCN'], function(ajaxPackage) {
    var userData={};
    // 验证码
    $("#verifycode").click(function(event) {
        /* Act on the event */
        $("#verifycode").attr('src', '/ValidateCode/getKaptchaImage?r=' + new Date().getTime());
    });

    $(".btn-login").click(function(event) {
        /* Act on the event */
        window.location.href = "/redirect/views/test/showUser";
    });

});