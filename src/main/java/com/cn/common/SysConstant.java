package com.cn.common;

import java.util.UUID;

/**
 * 常量
 * Created by 钟锐锋 on 2017/1/11.
 */
public class SysConstant {

//    session
    public static final String CURRENR_USER="current_user";
    public static final String CURRENR_ADDR = "current_addr";

    //db type
    public static final String DB_ORACLE="ORACLE";
    public static final String DB_MSSQL="MSSQL";
    public static final String DB_MYSQL="MYSQL";
    public static final String DB_ACCESS="ACCESS";
    public static final String DB_FOXPRO="FOXPRO";

    /**
     * 当主键为varchar(32)时，可用该函数产生主键
     * @return
     */
    public static String generateID(){
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

}