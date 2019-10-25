package com.zab.concurrenttest.thread;

/**
 * thread 方法测试
 *
 * @author zab
 * @date 2019-10-25 22:59
 */
public class ThreadMethodTest {

    static Thread2 thread2 = new Thread2();

    public static void main(String[] args) {
        new Thread(new Thread1()).start();
        thread2.start();
    }

    static class Thread1 implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "正在执行开始！！！！");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                thread2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "正在执行结束！！！！");
        }
    }

    static class Thread2 extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "正在执行开始！");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "正在执行结束!");
        }
    }
}
