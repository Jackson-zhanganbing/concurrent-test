package com.zab.concurrenttest.thread;

/**
 * 怎样启动一个线程
 *
 * @author zab
 * @date 2019-10-25 22:48
 */
public class HowToStratThread {

    public void f(){
        System.out.println(Thread.currentThread().getName()+"启动了！");
    }

    public static void main(String[] args) {
        //方式一：继承Thread类，重写run方法
        new Thread1().start();
        //方式二：实现Runnable接口，重写run方法
        new Thread(new Thread2()).start();
        //方式三：匿名内部类
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"启动了！");
            }
        }).start();
        //方式四：lambda表达式
        new Thread(()->System.out.println(Thread.currentThread().getName()+"启动了！")).start();

        //方式五：lambda表达式的特殊写法
        HowToStratThread t = new HowToStratThread();
        new Thread(t::f).start();
    }

    static class Thread1 extends Thread{
        @Override
        public void run(){
            System.out.println(Thread.currentThread().getName()+"启动了！");
        }
    }
    static class Thread2 implements Runnable{
        @Override
        public void run(){
            System.out.println(Thread.currentThread().getName()+"启动了！");
        }
    }
}
