// 封装ajax
define(['jquery', 'bootstrap'], function ($) {
    var base = {
        // url: config.url,
        type: 'post',
        dataType: 'json',
        data: {},
        timeout: 30000,
        cache: false,
        async: true
    };
    var Ajax = {
        request: function (config) {
            $.extend(base, config);
            $.ajax(base);
        },
        dowload:function(url,data){//ajax没有下载文件的功能，使用一个隐藏的表单来下载文件
            //data的格式是{"fileName":"xxxx.pdf"}
            var input="";
            $.each(data,function (key,value) {
                input+="<input type='hidden' name='"+key+"' value='"+value+"'/>";
            });
            $("<form action='"+url+"' method='post'>"+input+"</form>>").appendTo("body").submit().remove();
        }
    }
    return {
        'Ajax': Ajax
    }
});