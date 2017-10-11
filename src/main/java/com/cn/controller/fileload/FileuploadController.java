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

    @ResponseBody
    @RequestMapping("uploadMulFile")
    public JsonResult uploadMulFile(HttpServletRequest request){
        try {

        }catch (Exception e){
            e.printStackTrace();
            return new JsonResult(false,e.getMessage());
        }
        return new JsonResult(true,"文件上传成功","");
    }
}
