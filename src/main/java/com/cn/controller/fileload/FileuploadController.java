package com.cn.controller.fileload;

import com.cn.common.JsonResult;
import com.cn.controller.BaseController;
import com.cn.service.fileload.FileuploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.json.Json;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by 钟锐锋 on 2017/10/11.
 */
@Controller
@RequestMapping("/fileUpload")
public class FileuploadController extends BaseController{
    @Autowired
    FileuploadService fileuploadService;

    /**
     * 上传单个文件
     * fileupload插件上传多文件其实是一个一个文件上传
     * @param file
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("uploadFile")
    public JsonResult uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        Map<String,Object> params=super.requestParams(request);
        try {
            String realPath=request.getSession().getServletContext().getRealPath("/")+"uploadDir/fileUpload";
            String fileName=file.getOriginalFilename();
            fileuploadService.uploadFile(file.getInputStream(),params,realPath,fileName);
        }catch (Exception e){
            e.printStackTrace();
            return new JsonResult(false,e.getMessage());
        }
        return new JsonResult(true,"文件上传成功","");
    }

    /**
     * 同时多文件上传
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("uploadMulFile")
    public JsonResult uploadMulFile(HttpServletRequest request){
        JsonResult jsonResult=null;
        try {
            Map<String,Object> params=super.requestParams(request);
            //创建一个通用的多部分解析器
            CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(request.getSession().getServletContext());
            //判断request是否是多文件上传
            if(multipartResolver.isMultipart(request)){
                MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest) request;
                String fileName=fileuploadService.uploadMulFile(multiRequest,params);
                jsonResult=new JsonResult(true,"文件上传成功",fileName);
            }
        }catch (Exception e){
            e.printStackTrace();
               jsonResult= new JsonResult(false,e.getMessage());
        }
        return jsonResult;
    }
}
