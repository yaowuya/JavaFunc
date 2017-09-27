package com.cn.controller.fileload;

import com.cn.controller.BaseController;
import com.cn.entity.fileload.Plupload;
import com.cn.service.fileload.PluploadService;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Enumeration;
import java.util.Map;

/**
 * Created by 钟锐锋 on 2017/9/14.
 */
@Controller
@RequestMapping("/plupload")
public class PluploadController extends BaseController {
    @Autowired
    PluploadService pluploadService;
    @Autowired
    private HttpServletRequest request;

    /**Plupload文件上传处理方法*/
    @RequestMapping(value="/pluploadUpload")
    public void plupLoad(Plupload plupload, HttpServletRequest request,
                         HttpServletResponse response) {
        Map<String,Object> params=super.requestParams(request);
        String FileDir = "pluploadDir";//文件保存的文件夹
        plupload.setRequest(request);//手动传入Plupload对象HttpServletRequest属性
        String userId= MapUtils.getString(params,"userId");
        //文件存储绝对路径,会是一个文件夹，项目相应Servlet容器下的"pluploadDir"文件夹，还会以用户唯一id作划分
        File dir = new File(request.getSession().getServletContext().getRealPath("/") + FileDir+"/"+userId);
        if(!dir.exists()){
            dir.mkdirs();//可创建多级目录，而mkdir()只能创建一级目录
        }
        try {
            //开始上传文件
            pluploadService.upload(plupload, dir);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
