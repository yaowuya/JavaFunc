package com.cn.service.fileload;

import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
}
