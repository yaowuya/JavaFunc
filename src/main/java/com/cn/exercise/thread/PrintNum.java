package com.cn.exercise.thread;

/**
 * User: zhongrf
 * Date: 2018/6/5 16:01
 * Description:
 */
public class PrintNum implements Runnable{
    private int num;

    public PrintNum(int num){
        this.num=num;
    }

    @Override
    public void run(){
        for(int i=0;i<num;i++){
            System.out.print(" "+i);
            Thread.yield();
        }
        System.out.println("");
    }
}