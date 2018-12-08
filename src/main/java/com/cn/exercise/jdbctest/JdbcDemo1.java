package com.cn.exercise.jdbctest;

import java.sql.*;

/**
 * User: zhongrf
 * Date: 2018/4/26 13:41
 * Description:
 *   jdbc连接oracle、mysql等主流数据库的驱动类和url
     -----------------------------------------------------------
     oracle
     driverClass：oracle.jdbc.driver.OracleDriver
     url：jdbc:oracle:thin:@127.0.0.1:1521:dbname
     -----------------------------------------------------------
     mysql
     driverClass：com.mysql.jdbc.Driver
     url：jdbc:mysql://127.0.0.1:3306/javafunc
 */
class DaoException extends Exception{
    public DaoException(){
        super();
    }
    public DaoException(String msg){
        super(msg);
    }
}

public class JdbcDemo1 {
    public static void main(String[] args) {
        try {
            products();
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    public static void products() throws DaoException {

        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;

        JdbcConstant jdbcConstant=new JdbcConstant();

        try {
            Class.forName(jdbcConstant.getDriverName());
            System.out.println("开始尝试连接数据库！");
            connection= DriverManager.getConnection(jdbcConstant.getUrl(),jdbcConstant.getUserName(),jdbcConstant.getPassWord());
            System.out.println("连接成功！");
            String sql="select * from test_user";
            statement=connection.createStatement();
            resultSet=statement.executeQuery(sql);
            while (resultSet.next()){
                int id=resultSet.getInt("id");
                String userName=resultSet.getString("user_name");
                String passWord=resultSet.getString("password");
                int age=resultSet.getInt("age");
                System.out.println(id+" "+userName+" "+passWord+" "+age);
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new DaoException("数据库异常");
        }finally {
            // 逐一将上面的几个对象关闭，因为不关闭的话会影响性能、并且占用资源
            // 注意关闭的顺序，最后使用的最先关闭
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}

