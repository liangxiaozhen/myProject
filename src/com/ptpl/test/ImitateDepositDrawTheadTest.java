package com.ptpl.test;

/**
 * 多线程模拟一个存 一个取，存完通知另一个来取
 */
public class ImitateDepositDrawTheadTest {
     
    public static void main(String[] args) {
         
        final DepositOrWithdrawBusiness business = new DepositOrWithdrawBusiness(1000);
        final int n = 500;
        new Thread(new Runnable(){
            @Override
            public void run() {
                while(true){
                    business.deposit();
                }
            }
        }).start();
        new Thread(new Runnable(){
            @Override
            public void run() {
                while(true){
                    business.withdraw();
                }
            }
        }).start();
         
         
    }
}
class DepositOrWithdrawBusiness{
    int balance = 0;
    public DepositOrWithdrawBusiness(int balance){
        this.balance = balance;
    }
    boolean isWithdraw = false;
    public synchronized void deposit(){
        if(isWithdraw){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        balance = balance+300;
        System.out.println(Thread.currentThread().getName()+ " deposit $300,now balance:" + balance);
        isWithdraw=true;
        this.notify();
         
    }
    public synchronized void withdraw(){
        if(!isWithdraw){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        balance = balance-300;
        System.out.println(Thread.currentThread().getName()+ " withdraw $300,now balance:" + balance);
        isWithdraw=false;
        this.notify();
    }
}