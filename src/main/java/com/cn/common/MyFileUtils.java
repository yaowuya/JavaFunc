package com.cn.common;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by 钟锐锋 on 2017/10/12.
 */
public class MyFileUtils {

    /**
     * 保存多个文件
     *
     * @param files
     * @param savepath
     * @return
     */
    public static Map<String, Object> saveFiles(File[] files, String savepath) {
        //总保存结果
        Map<String, Object> resultMap = new HashMap<>();
        //每个保存的结果
        Map<String, Object> saveFileResultMap = new HashMap<>();
        //记录错误信息
        List<Object> msgList = new ArrayList<>();
        //记录总路径
        StringBuffer allSavePath = new StringBuffer();
        resultMap.put("result", true);
        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                String saveFilePath = savepath + File.separator + files[i].getName();
                saveFileResultMap = saveFile(files[i], savepath);
                if (!MapUtils.getBoolean(saveFileResultMap, "result", false)) {
                    resultMap.put("result", false);
                    msgList.add(MapUtils.getString(saveFileResultMap, "result"));
                } else {
                    allSavePath.append(saveFilePath).append(";");
                }
            }
        } else {
            resultMap.put("result", false);
            msgList.add("文件为空");
        }
        resultMap.put("msg", msgList);
        resultMap.put("allpath", allSavePath);
        return resultMap;
    }

    /**
     * 保存单个文件
     *
     * @param file
     * @param filePath
     * @return
     */
    public static Map<String, Object> saveFile(File file, String filePath) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", true);
        InputStream is = null;
        OutputStream os = null;
        try {
            //判断文件夹是否存在
            File saveFile = new File(filePath);
            if (!saveFile.exists() && !saveFile.isDirectory()) {
                saveFile.mkdirs();
            }
            String outPath = filePath + File.separator + file.getName();
            is = new FileInputStream(file);
            os = new FileOutputStream(outPath);

            byte[] buffer = new byte[1024 * 1024];
            int index = 0;
            try {
                while ((index = is.read(buffer)) != -1) {
                    os.write(buffer, 0, index);
                }
                os.flush();
            } catch (Exception e) {
                resultMap.put("result", false);
                resultMap.put("msg", e);
                throw e;
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("result", false);
            resultMap.put("msg", file.getName() + "没有找到");
        } finally {
            IOUtils.closeQuietly(os);
            IOUtils.closeQuietly(is);
        }
        return resultMap;
    }

    /**
     * 判断文件是否存在
     *
     * @param savePath
     * @param fileName
     * @return
     */
    public static Map<String, Object> checkFile(String savePath, String fileName) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", true);
        try {
            if (Files.exists(Paths.get(savePath, fileName))) {
                resultMap.put("result", false);
                resultMap.put("msg", "该文件" + fileName + "已经存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * 文件是通过MultipartFile接收的
     * 保存文件
     *
     * @param file
     * @param savePath
     * @return
     */
    public static Map<String, Object> saveMultipartFile(MultipartFile file, String savePath) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", true);
        try {
            File targetFile = new File(savePath, file.getOriginalFilename());
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("result", false);
            resultMap.put("msg", file.getOriginalFilename() + "文件上传失败");
        }
        resultMap.put("msg", file.getOriginalFilename());
        return resultMap;
    }

    /**
     * 删除文件
     *
     * @param filePath
     * @return
     */
    public static boolean deleteFileByPath(String filePath) {
        return deleteFile(new File(filePath));
    }

    /**
     * 删除文件
     *
     * @param file
     * @return
     */
    public static boolean deleteFile(File file) {
        boolean flag = true;
        if (file != null) {
            try {
                if (file.isFile()) {
                    flag = file.delete();
                }
            } catch (Exception e) {
                e.printStackTrace();
                flag = false;
            }
        }
        return flag;
    }

    /**
     * 判断文件类型
     *
     * @param fileName
     * @return
     */
    public static String checkFileType(String fileName) {
        String type = "";
        try {
            if (fileName != null && "".equals(fileName)) {
                String sufix = fileName.substring(fileName.lastIndexOf(".") + 1).toUpperCase();
//                String fileType= FilenameUtils.getExtension(fileName);
                if (sufix.equals("DOC") || sufix.equals("DOCX") || sufix.equals("DOCM")
                        || sufix.equals("DOTM") || sufix.equals("DOTX")) {
                    type = "WORD";
                }
                if (sufix.equals("XLS") || sufix.equals("XLSX") || sufix.equals("XLSM") ||
                        sufix.equals("XLTM") || sufix.equals("XLTX")) {
                    type = "EXCEL";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return type;
    }

    /**
     * 文件压缩下载
     *
     * @param response
     * @param filePath    是完整的路径，包括文件名
     * @param fileZipName
     */
    public static void downLoad(HttpServletResponse response, String filePath, String fileZipName) {
        try {
            filePath = URLDecoder.decode(filePath, "utf-8");
            response.setContentType("application/zip");
            response.setHeader("Cache-Control", "no-cache");
            String downloadFileName = fileZipName + ".zip";
            response.setHeader("Content-Disposition", "attachment;filename=" + downloadFileName);
            downLoadFile(response.getOutputStream(), filePath.split(";"), downloadFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 下载压缩文件
     * @param outputStream  response.getOutputStream()
     * @param filePaths 该参数为文件完整路径，包含文件名
     * @param zipFileName  压缩文件的名称
     * @return
     */
    public static boolean downLoadFile(ServletOutputStream outputStream, String[] filePaths, String zipFileName) {
        //创建zip输出流
        ZipOutputStream zos = new ZipOutputStream(outputStream);
        try {
            FileInputStream fis = null;
            File file = null;
            zos.setEncoding((String) System.getProperty("sun.zip.encoding"));
            //用来记录读取的日志
            StringBuffer msg = new StringBuffer();
            for (String filePath : filePaths) {
                try {
                    String result = checkFileExits(filePath);
                    if ("".equals(result)) {
                        file = new File(filePath);
                        fis = new FileInputStream(file);
                        zipOutputStreamWrite(zos, fis, file);
                    }
                    msg.append(result);
                } catch (Exception e) {
                    msg.append("读取文件失败:<br/>").append(filePath).append("<br/>");
                    msg.append(e.getMessage());
                    throw e;
                } finally {
                    try {
                        if (fis != null) {
                            fis.close();
                        }
                    } catch (IOException e) {
                        throw e;
                    }
                }
            }
            //将错误信息写入压缩文件中
            if (msg.length() > 0) {
                zipErrorMsg(zos, msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (zos != null) {
                    zos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }


    /**
     * 检查文件是否存在
     *
     * @param filePath 单纯的文件路径
     * @param fileName
     * @return
     */
    public static String checkFileExits(String filePath, String fileName) {
        if (!filePath.endsWith(File.separator)) {
            filePath += File.separator;
        }
        return checkFileExits(filePath + fileName);
    }

    /**
     * 检查文件是否存在
     *
     * @param filePath 该参数为文件完整路径，包含文件名
     * @return
     */
    public static String checkFileExits(String filePath) {
        String result = "";
        if (StringUtils.isNotBlank(filePath)) {
            if (!Files.exists(Paths.get(filePath))) {
                result = "文件:<br/>" + filePath + " 不存在<br/>";
            }
        } else {
            result = "文件:<br/>" + filePath + " 读取失败<br/>";
        }
        return result;
    }

    public static void zipOutputStreamWrite(ZipOutputStream zos, FileInputStream fis, File file) throws IOException {
        String fileName = file.getName();
        zipOutputStreamWrite(zos, fis, fileName);
    }

    /**
     * 压缩文件
     *
     * @param zos
     * @param fis
     * @param fileName
     * @throws IOException
     */
    public static void zipOutputStreamWrite(ZipOutputStream zos, FileInputStream fis, String fileName) throws IOException {
        byte[] buff = new byte[1024 * 1024];
        int len;
        ZipEntry zipEntry = new ZipEntry(fileName);
        zos.putNextEntry(zipEntry);
        while ((len = fis.read(buff)) != -1) {
            zos.write(buff, 0, len);
        }
    }

    /**
     * 直接压缩一条字符串信息
     *
     * @param zos
     * @param msg
     * @throws IOException
     */
    public static void zipErrorMsg(ZipOutputStream zos, StringBuffer msg) throws IOException {
        byte[] byteStr = msg.toString().getBytes();
        ZipEntry ze = new ZipEntry("下载错误日志.txt");
        zos.putNextEntry(ze);
        zos.write(byteStr, 0, byteStr.length);
        zos.flush();
    }

    /**
     * 单个文件下载
     * @param request
     * @param response
     * @param filePath 完整的文件路径，包含文件名
     * @throws UnsupportedEncodingException
     */
    public static void fileDownLoad(HttpServletRequest request, HttpServletResponse response,String filePath) throws UnsupportedEncodingException {
        File file=new File(filePath);
        String fileName=file.getName();
        //设置文件ContentType类型，这样设置，系统会自动判断下载的文件类型
        response.setContentType("application/x-download");
        String agent=request.getHeader("User-Agent");
        if(agent!=null&&agent.toLowerCase().indexOf("firefox")>0){
            fileName="=?UTF-8?B"+(new String(Base64.encodeBase64(fileName.getBytes("UTF-8"))));
        }else{
            fileName= URLEncoder.encode(fileName,"UTF-8");
            fileName=fileName.replaceAll("\\+","%20").replaceAll("%28","\\(").replaceAll("%29","\\)").
                    replaceAll("%40","\\@").replaceAll("%24","\\$").replaceAll("%3B",";").replaceAll("%2C",",").
                    replaceAll("%26","\\&").replaceAll("%23","\\#").replaceAll("%2B","\\+").replaceAll("%2F","\\/");
        }
        //文件名外的双引号是处理firefox的空格截断问题
        response.setHeader("Content-Disposition", String.format("attachment;filename=\"%s\"",fileName));
        ServletOutputStream out;
        try {
            FileInputStream inputStream=new FileInputStream(file);
            out=response.getOutputStream();
            int len=0;
            byte[] buffer=new byte[1024];
            while ((len=inputStream.read(buffer))!=-1){
                out.write(buffer,0,len);
            }
            inputStream.close();
            if(out!=null){
                out.flush();
                out.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
