package com.woniuxy;

import java.util.concurrent.TimeUnit;

public class Main {

    private static Integer num = 100;

    public static void main(String[] args) throws InterruptedException {

        //创建并执行一个线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                //线程要执行的内容
                for (int i=1;i<101;i++){
                    synchronized (Main.class) {  //同步代码块
                        num--;
                        System.out.println(Thread.currentThread().getName() + "  " + num);
                    }
                }
            }
        }).start();


        //创建并执行一个线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                //线程要执行的内容
                for (int i=1;i<101;i++){
                    synchronized (Main.class) {   //同步代码块
                        num++;
                        System.out.println(Thread.currentThread().getName() + "  " + num);
                    }
                }
            }
        }).start();


        TimeUnit.SECONDS.sleep(20);
        System.out.println(num);

    }




}