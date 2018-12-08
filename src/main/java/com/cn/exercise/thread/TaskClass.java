package com.cn.exercise.thread;

/**
 * User: zhongrf
 * Date: 2018/6/5 15:51
 * Description:
 */
public class TaskClass implements Runnable {
    @Override
    public void run() {
        Thread thread5=new Thread(new PrintChar('c',40));
        thread5.start();
        try {
            for(int i=0;i<100;i++){
                System.out.print(" d");
                if(i==50){
                    thread5.join();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}