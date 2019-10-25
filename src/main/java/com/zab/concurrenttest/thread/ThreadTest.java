package com.zab.concurrenttest.thread;

/**
 * thread 基础测试  run和start的区别
 *
 * @author zab
 * @date 2019-10-25 22:32
 */
public class ThreadTest {

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        //注意这里如果直接调用run方法，则main方法中就只有一条执行路径
        myThread.run();
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName()+"-----"+i);
        }
    }

    static class MyThread extends Thread{
        @Override
        public void run(){
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName()+"-----"+i);
            }
        }
    }
}
