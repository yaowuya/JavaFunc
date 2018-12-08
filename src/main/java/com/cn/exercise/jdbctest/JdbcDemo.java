package com.cn.exercise.jdbctest;

import javax.xml.transform.Result;
import java.sql.*;

/**
 * User: zhongrf
 * Date: 2018/6/7 11:16
 * Description:
 */
public class JdbcDemo {
    public static void main(String[] args) {
        String driverName="com.mysql.jdbc.Driver";
        String url="jdbc:mysql://127.0.0.1:3306/javafunc";
        String userName="root";
        String passWord="root";

        Connection con=null;
        PreparedStatement stat=null;
        ResultSet rs=null;

        try {
            Class.forName(driverName);
            con= DriverManager.getConnection(url,userName,passWord);
            String sql="select * from test_user";
            stat=con.prepareStatement(sql);
            rs=stat.executeQuery();
            while (rs.next()){
                int id=rs.getInt("id");
                String user=rs.getString("user_name");
                String pass=rs.getString("password");
                int age=rs.getInt("age");
                System.out.println(id+" "+user+" "+pass+" "+age);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                rs.close();
                stat.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}