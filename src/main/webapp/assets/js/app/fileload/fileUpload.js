/**
 * Created by zhongrf on 2017/10/9.
 */
define(['bootstrap', 'fileinput_zh'], function () {

    fileInput("input-file", "user/uploadfile");
    function fileInput(elementId, uploadUrl) {
        var control = $('.' + elementId);
        //初始化上传控件的样式
        control.fileinput({
            language: 'zh', //设置语言
            uploadUrl: uploadUrl, //上传的地址
            allowedFileTypes: ['image'],//配置允许文件上传的类型
            allowedPreviewTypes: ['image'],//配置所有的被预览文件类型
            allowedFileExtensions: ['jpg', 'gif', 'png', 'txt', 'docx', 'xlsx'],//接收的文件后缀
            showUpload: true, //是否显示上传按钮
            showRemove: true, //显示移除按钮,跟随文本框的那个
            showCaption: true,//是否显示标题(输入框)
            browseClass: "btn btn-primary", //按钮样式
            dropZoneEnabled: false,//是否显示拖拽区域
            // minImageWidth: 50, //图片的最小宽度
            // minImageHeight: 50,//图片的最小高度
            // maxImageWidth: 1000,//图片的最大宽度
            // maxImageHeight: 1000,//图片的最大高度
            // maxFileSize: 0,//单位为kb，如果为0表示不限制文件大小
            minFileCount: 0,
            maxFileCount: 10, //表示允许同时上传的最大文件个数
            enctype: 'multipart/form-data',
            validateInitialCount: true,
            previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
            msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
        });
        //导入文件上传完成之后的事件
        control.on("fileuploaded", function (event, data, previewId, index) {
            console.log(event, data, previewId, index);
        });
    }
});
