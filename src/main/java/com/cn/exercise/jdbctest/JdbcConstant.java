package com.cn.exercise.jdbctest;

/**
 * User: zhongrf
 * Date: 2018/4/26 13:46
 * Description:
 */
public class JdbcConstant {

    private String driverName;
    private String url;
    private String userName;
    private String passWord;

    public JdbcConstant(){
        this.driverName="com.mysql.jdbc.Driver";
        this.url="jdbc:mysql://127.0.0.1:3306/javafunc";
        this.userName="root";
        this.passWord="root";
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}