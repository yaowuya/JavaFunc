package com.cn.entity.data;

import com.cn.common.SysConstant;
import org.apache.commons.dbcp.BasicDataSource;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

/**
 * Created by 钟锐锋 on 2017/11/10.
 */
public class MyDataSource extends BasicDataSource {

    //初始化连接大小
    private int initialSize=10;
    //连接池最大数量
    private int maxActive=20;
    //连接池最大空闲
    private int maxIdle=20;
    //连接池最小空闲
    private int minIdle=1;
//    创建或者废弃连接池是否写日志
    private boolean logAbandoned=false;

    private long id;
    private String name;
    private String type;
    private String isRAC;
    private String database;
    private String port;

    public MyDataSource() {
    }

    public MyDataSource(String url,String type,String database,String port,
                        String username,String password,String isRAC){
        this.setUrl(url);
        this.setType(type);
        this.setDatabase(database);
        this.setPort(port);
        this.setUsername(username);
        this.setPassword(password);
        this.setIsRAC(isRAC);
    }

    /**
     * 初始化连接池
     */
    public void initialMyDataSource(){
        String url=null;
        String dbtype=this.type.trim().toUpperCase();
        String driClazz=null;
        if(dbtype.equals(SysConstant.DB_ORACLE)){
            driClazz="oracle.jdbc.driver.OracleDriver";
            if(isRAC.equals("1")){
                url="jdbc:oracle:thin:@"+this.getUrl();
            }else{
                url="jdbc:oracle:thin:@"+this.getUrl()+":"+this.port+":"+this.database;
            }
        }else if(dbtype.equals(SysConstant.DB_MYSQL)){
            driClazz="aorg.gjt.mm.mysql.Driver";
            url="jdbc:mysql://"+this.getUrl()+":"+this.port+"/"+this.database;
        }else if(dbtype.equals(SysConstant.DB_MSSQL)){
            driClazz="com.microsoft.sqlserver.jdbc.SQLServerDriver";
            url="jdbc:sqlserver://"+this.getUrl()+":"+this.port+";DatabaseName="+this.database;
        }else if(dbtype.equals(SysConstant.DB_ACCESS)){
            driClazz="sun.jdbc.odbc.JdbcOdbcDriver";
            url="jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ="+this.database+";DriverID=22;READONL";
        }else if(dbtype.equals(SysConstant.DB_FOXPRO)){
            driClazz="sun.jdbc.odbc.JdbcOdbcDriver";
            url="jdbc:odbc:Driver=={Microsoft Visual FoxPro Driver};SourceType=DBF;SourceDB="+this.database;
        }
       // 初始化连接池
        this.setUrl(url);
        this.setDriverClassName(driClazz);
        this.setInitialSize(initialSize);
        this.setMaxActive(maxActive);
        this.setMaxIdle(maxIdle);
        this.setMinIdle(minIdle);
        this.setLogAbandoned(logAbandoned);
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public int getInitialSize() {
        return initialSize;
    }

    @Override
    public void setInitialSize(int initialSize) {
        this.initialSize = initialSize;
    }

    @Override
    public int getMaxActive() {
        return maxActive;
    }

    @Override
    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    @Override
    public int getMaxIdle() {
        return maxIdle;
    }

    @Override
    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    @Override
    public int getMinIdle() {
        return minIdle;
    }

    @Override
    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public boolean isLogAbandoned() {
        return logAbandoned;
    }

    @Override
    public void setLogAbandoned(boolean logAbandoned) {
        this.logAbandoned = logAbandoned;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsRAC() {
        return isRAC;
    }

    public void setIsRAC(String isRAC) {
        this.isRAC = isRAC;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public void setLoginTimeout(int loginTimeout) throws SQLException {

    }

    @Override
    public void setLogWriter(PrintWriter logWriter) throws SQLException {
        super.setLogWriter(logWriter);
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
