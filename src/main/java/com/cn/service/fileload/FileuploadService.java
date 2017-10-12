package com.cn.service.fileload;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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

    public String uploadMulFile(MultipartHttpServletRequest multiRequest, Map<String, Object> params) {
        //去掉request所有文件名
        Iterator<String> iter=multiRequest.getFileNames();
        List<String> fileNames=new ArrayList<>();
        String str="";
        try {
            while (iter.hasNext()){
                MultipartFile file=multiRequest.getFile(iter.next());
                if(file!=null){

                }
            }
            return str.substring(0,str.length()-1);
        }catch (Exception e){
            throw e;
        }
    }
}
