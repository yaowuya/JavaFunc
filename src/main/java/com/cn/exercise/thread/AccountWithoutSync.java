package com.cn.exercise.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * User: zhongrf
 * Date: 2018/6/6 9:15
 * Description:
 */
public class AccountWithoutSync {
    private static Account account=new Account();

    public static void main(String[] args) {
        ExecutorService executorService= Executors.newCachedThreadPool();

        for(int i=1;i<=100;i++){
            executorService.execute(new AddPennyTask());
        }

        executorService.shutdown();

        while(!executorService.isTerminated()){

        }

        System.out.println("what is balance?"+account.getBalance());
    }

    private static class AddPennyTask implements Runnable{
        @Override
        public void run() {
            account.deposit(1);
        }
    }

    private static class Account{
       private int balance=0;

       public int getBalance(){
           return this.balance;
       }

       public synchronized void deposit(int amount){
           int newBalance=amount+this.balance;
           try {
               Thread.sleep(5);
           }catch (Exception e){
               e.printStackTrace();
           }

           this.balance=newBalance;
       }
    }
}