package com.cn.service.fileload;

import com.cn.common.MyFileUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by 钟锐锋 on 2017/10/11.
 */
@Service
public class FileuploadService {

    public void uploadFile(InputStream inputStream, Map<String, Object> params, String realPath, String fileName) throws Exception {
        if(!Files.exists(Paths.get(realPath))){
            Files.createDirectories(Paths.get(realPath));
        }
        if(Files.exists(Paths.get(realPath,fileName))){
            throw new Exception("该文件已经存在");
        }
        Files.copy(inputStream,Paths.get(realPath,fileName), StandardCopyOption.REPLACE_EXISTING);
    }

    /**
     * 多文件上传
     * @param multiRequest
     * @param params
     * @return
     */
    public String uploadMulFile(MultipartHttpServletRequest multiRequest, Map<String, Object> params) throws Exception {
        //去掉request所有文件名
        Iterator<String> iter=multiRequest.getFileNames();
        List<String> fileNames=new ArrayList<>();
        //保存路径格式 xxxx/xxx/xxx
        String savePath= MapUtils.getString(params,"savePath");
        savePath+= File.separator;
        String str="";
        try {
            while (iter.hasNext()){
                MultipartFile file=multiRequest.getFile(iter.next());
                if(file!=null){
                   if(Files.exists(Paths.get(savePath,file.getOriginalFilename()))){
                       throw new Exception("该文件已经存在,请重新上传");
                   }else{
                       fileNames.add(file.getOriginalFilename());
                       str+=file.getOriginalFilename()+";";
                       MyFileUtils.saveMultipartFile(file,savePath+file.getOriginalFilename());
                   }
                }
            }
            return str.substring(0,str.length()-1);
        }catch (Exception e){
            if(!fileNames.isEmpty()){
                for(String fileName:fileNames){
                    MyFileUtils.deleteFileByPath(savePath+fileName);
                }
            }
            throw e;
        }
    }
}
