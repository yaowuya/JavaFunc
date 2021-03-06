/**
 * Created by zhongrf on 2017/9/29.
 */
define(['pluploadUi','plup_zh_CN'],
    function () {
        $("#uploader").plupload({
            // General settings
            runtimes: 'html5,flash,silverlight,html4', //用来指定上传方式，指定多个上传方式请使用逗号隔开。一般情况下，你不需要配置该参数，因为Plupload默认会根据你的其他的参数配置来选择 最合适的上传方式。
            url: "/plupload/pluploadUpload",
            chunk_size: '1mb',
            // Resize images on clientside if we can
            resize: {
                width: 200,
                height: 200,
                quality: 90,
                crop: true // crop to exact dimensions
            },
            // Specify what files to browse for
            filters: {
                mime_types: [ //对上传文件的格式做出限制，title为标识，extensions为允许上传文件的后缀名（配置改这个）
                    {title: "Image files", extensions: "jpg,gif,png"}
                    , {title: "Zip files", extensions: "zip,avi"}
                    , {title: "XLS files", extensions: "xls,xlsx"}
                    , {title: "Word files", extensions: "txt,doc"}
                ],
                max_file_size: '400mb', //最大只能上传400mb的文件
                prevent_duplicates: true //不允许选取重复文件
            },
            // Rename files by clicking on their titles
            rename: true,

            // Sort files
            sortable: true,

            // Enable ability to drag'n'drop files onto the widget (currently only HTML5 supports that)
            dragdrop: true,
            // Views to activate
            views: {
                list: true,
                thumbs: true, // Show thumbs
                active: 'thumbs'
            },
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
    });
