package com.cn.exercise.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * User: zhongrf
 * Date: 2018/6/6 9:45
 * Description:
 */
public class ThreadCooperation {
    private static Account account=new Account();

    public static void main(String[] args) {
        ExecutorService executorService= Executors.newFixedThreadPool(2);

        executorService.execute(new DepositTask());
        executorService.execute(new WithDrawTask());

        executorService.shutdown();
    }

    public static class DepositTask implements Runnable{

        @Override
        public void run() {
            try {
                while (true){
                    account.deposit((int)(Math.random()*10)+1);
                    Thread.sleep(1000);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static class WithDrawTask implements Runnable{

        @Override
        public void run() {
            while (true){
                account.withDraw((int)(Math.random()*10)+1);
            }
        }
    }
    private static class Account{
        private static Lock lock=new ReentrantLock();
        private static Condition newDeposit=lock.newCondition();
        private int balance=0;

        public int getBalance(){
            return this.balance;
        }

        public void withDraw(int amount){
            lock.lock();
            try {
                while (balance<amount){
                    System.out.println("wait for a deposit");
                    newDeposit.await();
                }
                balance-=amount;
                System.out.println("widhdraw amount:"+amount+" balance:"+this.balance);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }

        public void deposit(int amount){
            lock.lock();
            try {
                balance+=amount;
                System.out.println("deposit amount:"+amount+" balance:"+this.balance);
                newDeposit.signalAll();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }

    }
}