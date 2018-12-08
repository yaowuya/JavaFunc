package com.cn.exercise.jdbctest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Scanner;

/**
 * User: zhongrf
 * Date: 2018/4/27 17:12
 * Description:
 */
public class jdbcDemo2 {

    public static void main(String[] args) {
//        String sql="select * from test_user";
        StringBuilder sql=new StringBuilder();
        Scanner in = new Scanner(System.in);
        sql.append(in.nextLine().trim());
        System.out.println(sql.toString());
        jdbcProperties(sql.toString());
    }

    public static void jdbcProperties(String sql){
        Properties properties=new Properties();
        Connection connection=null;
        PreparedStatement pst=null;
        ResultSet rs=null;
        try {
//            InputStream inputStream=new BufferedInputStream(new FileInputStream("/src/main/resources/jdbc.properties"));
            /*path 不以’/'开头时默认是从此类所在的包下取资源，以’/'开头则是从
            ClassPath根下获取。其只是通过path构造一个绝对路径，最终还是由ClassLoader获取资源。 */
            properties.load(jdbcDemo2.class.getClassLoader().getResourceAsStream("jdbc.properties"));

            String driver=properties.getProperty("jdbc.driver");
            String url=properties.getProperty("jdbc.url");
            String user=properties.getProperty("jdbc.username");
            String password=properties.getProperty("jdbc.password");

            Class.forName(driver);
            connection= DriverManager.getConnection(url,user,password);
            pst=connection.prepareStatement(sql);
            if(sql.startsWith("select")){
                rs=pst.executeQuery();
                while (rs.next()){
                    int id=rs.getInt("id");
                    String userName=rs.getString("user_name");
                    String passWord=rs.getString("password");
                    int age=rs.getInt("age");
                    System.out.println(id+" "+userName+" "+passWord+" "+age);
                }
            }else if(sql.startsWith("insert")){
                pst.executeUpdate();
                System.out.println("增加一条数据");
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(rs!=null){
                    rs.close();
                }
                if(pst!=null){
                    pst.close();
                }

                if(connection!=null){
                    connection.close();
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}