package com.cn.exercise.thread;

/**
 * User: zhongrf
 * Date: 2018/6/5 15:48
 * Description:
 */
public class Thread1 {
    public static void main(String[] args) {
        PrintChar printChar=new PrintChar('a',100);
        PrintChar printChar1=new PrintChar('b',100);
        PrintNum printNum=new PrintNum(100);
        TaskClass taskClass=new TaskClass();

        Thread thread1=new Thread(printChar);
        Thread thread2=new Thread(printChar1);
        Thread thread3=new Thread(printNum);
        Thread thread4=new Thread(taskClass);


        thread1.start();
        thread3.start();
        thread4.start();
        thread2.start();

    }
}