package com.cn.common.utils;

import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.io.ZipOutputStream;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;
import org.apache.commons.lang.StringUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 经常服务器需要对文件进行压缩，网络上流传较多的是从磁盘文件中来压缩成zip文件。
 * 但是常常服务器的文件存放在内存中，以byte[]形式存储在内存中。
 * 这个时候就不能使用网络上流传的常用方法了，这里就需要对内存文件进行压缩。
 * 通过内存文件来压缩成zip的方式，首先性能方面比磁盘压缩要快很多，
 * 另外内存文件压缩到方式不会产生临时文件或者磁盘文件，而磁盘读取文件方式压缩，
 * 会产生新的zip文件。然后要说下的是，内存压缩方式可以支持内存文件压缩，
 * 也可以手动将磁盘文件读取到内存然后在进行压缩，这样也不会产生新的临时文件。
 * <p>
 * 对内存文件和流文件的压缩以避免产生临时文件
 * 该工具类支持加密和非加密两种压缩方式
 */
public class ZipUtils {
    /**
     * @param fileName 文件名
     * @param data     文件数据
     * @param password 密码
     * @desc 将内存文件写入zip内。注意：最后必须调用closeZipOutputStream关闭输出流，或者手动关闭
     */
    public static void addFileToZip(String fileName, byte[] data, String password, ZipOutputStream zipOutputStream)
            throws ZipException, IOException {

        if (StringUtils.isEmpty(fileName) || data == null || data.length == 0 || zipOutputStream == null) {
            throw new ZipException(new StringBuilder("参数异常,fileName=").append(fileName).append(",data=").append(data)
                    .append(",zipOutputStream=").append(zipOutputStream).toString());
        }

        ZipParameters zipParameters = new ZipParameters();
        zipParameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE); // 压缩方式
        zipParameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL); // 压缩级别

        zipParameters.setFileNameInZip(fileName);

        if (StringUtils.isNotBlank(password)) {
            zipParameters.setEncryptFiles(true);
            zipParameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_STANDARD);
            zipParameters.setPassword(password.toCharArray());
        }

        // 源文件是否为外部流，true表示使用内存文件而非本地存储文件
        zipParameters.setSourceExternalStream(true);

        zipOutputStream.putNextEntry(null, zipParameters);
        zipOutputStream.write(data);
        zipOutputStream.closeEntry();
    }

    /**
     * @param fileName 文件名
     * @param data     文件数据
     * @desc 将内存文件写入zip内。注意：最后必须调用closeZipOutputStream关闭输出流，或者手动关闭
     */
    public static void addFileToZip(String fileName, byte[] data, ZipOutputStream zipOutputStream)
            throws ZipException, IOException {
        addFileToZip(fileName, data, null, zipOutputStream);
    }

    /**
     * @param zipParameters   zip参数
     * @param data            文件数据
     * @param zipOutputStream 输出流
     * @desc 将内存文件写入zip内。注意：最后必须调用closeZipOutputStream关闭输出流，或者手动关闭
     * @auth josnow
     * @date 2017年5月25日 上午11:08:56
     */
    public static void addFileToZip(ZipParameters zipParameters, byte[] data, ZipOutputStream zipOutputStream)
            throws ZipException, IOException {

        if (zipParameters == null || data == null || data.length == 0 || zipOutputStream == null) {
            throw new ZipException(new StringBuilder("参数异常,zipParameters=").append(zipParameters).append(",data=")
                    .append(data).append(",zipOutputStream=").append(zipOutputStream).toString());
        }
        zipOutputStream.putNextEntry(null, zipParameters);
        zipOutputStream.write(data);
        zipOutputStream.closeEntry();
    }

    /**
     * @param zipOutputStream 输出流
     * @desc 关闭流
     */
    public static void closeZipOutputStream(ZipOutputStream zipOutputStream) throws IOException, ZipException {
        if (zipOutputStream == null) {
            return;
        }
        zipOutputStream.finish();
        zipOutputStream.close();
    }

    public static void main(String[] args) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new
                ByteArrayOutputStream(1024);
        ZipOutputStream zipOutputStream = new
                ZipOutputStream(byteArrayOutputStream);

        byte[] b = "德玛西亚哦哦奥法额外发撒旦；联发科就；".getBytes();

        addFileToZip("你好大猪头.txt", b, zipOutputStream);
        addFileToZip("你就是大肥猪.txt", b, "123", zipOutputStream);

        closeZipOutputStream(zipOutputStream);

        byte[] zipData = byteArrayOutputStream.toByteArray();
        System.out.println(new String(zipData));

        new FileOutputStream("C:/Users/zhongrf/Desktop/JavaFunc/src/main/webapp/uploadDir/zipDir/name.zip").write(zipData);
    }
}
