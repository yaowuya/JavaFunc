package com.cn.exercise.thread;

/**
 * User: zhongrf
 * Date: 2018/6/5 16:01
 * Description:
 */
public class PrintChar implements Runnable {
    private char charToPrint;
    private int times;

    public PrintChar(char charToPrint,int times){
        this.charToPrint=charToPrint;
        this.times=times;
    }

    @Override
    public void run() {
        for(int i=0;i<times;i++){
            System.out.print(" "+charToPrint);
        }
        System.out.println("");
    }
}