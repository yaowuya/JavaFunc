// Initialize the widget when the DOM is ready
var uploader = $("#uploader").pluploadQueue({
    // General settings
    runtimes: 'html5,flash,silverlight,html4', //用来指定上传方式，指定多个上传方式请使用逗号隔开。一般情况下，你不需要配置该参数，因为Plupload默认会根据你的其他的参数配置来选择 最合适的上传方式。
    url: "/plupload/pluploadUpload",
    // Maximum file size

    chunk_size: '1mb',

    // Resize images on clientside if we can
    resize: {
        width: 200,
        height: 200,
        quality: 90,
        crop: true // crop to exact dimensions
    },

    // 可以使用该参数来限制上传文件的类型，大小等，该参数以对象的形式传入
    filter: {
        mime_types: [ //只允许上传图片,视频和zip文件
            {
                title: "Image files",
                extensions: "jpg,gif,png"
            }, {
                title: "Vedio files",
                extensions: "mp4,mkv"
            }, {
                title: "Zip files",
                extensions: "zip,avi"
            }
        ],
        max_file_size: '10000mb',
        prevent_duplicates: true //不允许选取重复文件
    },
    // Rename files by clicking on their titles
    // rename: true,

    // Sort files
    sortable: true,

    // Enable ability to drag'n'drop files onto the widget (currently only HTML5 supports that)
    dragdrop: true,

    // Flash settings
    flash_swf_url: '../../plugins/plupload-2.3.3/Moxie.swf',

    // Silverlight settings
    silverlight_xap_url: '../../plugins/plupload-2.3.3/Moxie.xap',
    // 参数  
    multipart_params: {
        'userId': parseInt(100 * Math.random()),
        'userName': 'ruifeng',
        'time': '2017-09-14'
    }
});

$("#toStop").on('click', function() {
    uploader.stop();
});

$("#toStart").on('click', function() {
    uploader.start();
});