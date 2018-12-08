package com.cn.controller.thread;

import java.util.HashSet;
import java.util.Set;

/**
 * User: zhongrf
 * Date: 2018/4/19 14:52
 * Description:
 */
public class ThreadTest{
    public static void main(String[] args) {
        System.out.println("主线程ID:"+Thread.currentThread().getId());
        MyThread thread1 = new MyThread("thread1");
        thread1.start();
        MyThread thread2 = new MyThread("thread2");
        thread2.run();
    }
}

class MyThread extends Thread{
    private String name;

    public MyThread(String name){
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("name:"+name+" 子线程ID:"+Thread.currentThread().getId());
    }
}
