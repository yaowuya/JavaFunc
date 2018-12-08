package com.cn.exercise.file;

import org.apache.commons.lang.StringUtils;

import java.io.File;

/**
 * User: zhongrf
 * Date: 2018/6/21 10:30
 * Description: 递归读取文件夹下的 所有文件
 */
public class loopOutAllFile {
    public static void main(String[] args) {
        String filePath="C:\\Users\\zhongrf\\Downloads";
        testLoopOutFiles(filePath);
    }
    public static void testLoopOutFiles(String dir){
        if(StringUtils.isBlank(dir)){
            //因为new File(null)会空指针异常,所以要判断下
            return;
        }

        File files=new File(dir);
        File[] fileList=files.listFiles();
        if(fileList==null){
            return;
        }
        for(File file:fileList){
            if (file.isFile()) {
                System.out.println(file.getName());
            } else if (file.isDirectory()) {
                System.out.println(file.getAbsolutePath() +"  是一个目录, 目录中的文件为:");
                testLoopOutFiles(file.getPath());
            } else {
                System.out.println("文件读入有误！");
            }
        }
    }

}