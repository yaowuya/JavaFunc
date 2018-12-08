package com.cn.controller.thread;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: zhongrf
 * Date: 2018/4/19 15:10
 * Description:
 */
public class RunableTest {
    public static void main(String[] args) {
        System.out.println("主线程ID："+Thread.currentThread().getId());
        MyRunnable myRunnable=new MyRunnable();
        Thread thread=new Thread(myRunnable);
        thread.start();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuilder str=new StringBuilder("2018-04-20 15:56:19.0");
        SimpleDateFormat sdf1=new SimpleDateFormat("YYYY-MM-dd HH:MM:ss");
//        Date date=new Date("2018-04-20 15:20:00");
        try {
            Date data=sdf.parse(str.toString());
            System.out.println(data);
            String dateStr=sdf1.format(data);
            System.out.println(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}

class MyRunnable implements Runnable{
    public MyRunnable() {
    }

    @Override
    public void run() {
        System.out.println("子线程ID："+Thread.currentThread().getId());
    }
}