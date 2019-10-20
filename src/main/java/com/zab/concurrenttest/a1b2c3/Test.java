package com.zab.concurrenttest.a1b2c3;

import java.util.concurrent.Semaphore;

/**
 * 题目：两个线程，A线程输出a，b，c。。。B线程输出1，2，3。。。最终结果是a1b2c3。。。
 *
 * @author zab
 * @date 2019-10-16 22:13
 */
public class Test {

    static Semaphore aSemaphore = new Semaphore(1);
    static Semaphore oneSemaphore = new Semaphore(0);


    public static void main(String[] args) {
        new Thread(new ABC()).start();
        new Thread(new OneTwoThree()).start();
    }

    static class ABC implements Runnable {

        private String[] abcs = {"a", "b", "c"};

        @Override
        public void run() {
            for (int i = 0, j = abcs.length; i < j; i++) {
                try {
                    aSemaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print(abcs[i]);
                oneSemaphore.release();
            }

        }
    }

    static class OneTwoThree implements Runnable {

        private String[] onetwothrees = {"1","2","3"};

        @Override
        public void run() {

            for (int i = 0, j = onetwothrees.length; i < j; i++) {
                try {
                    oneSemaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print(onetwothrees[i]);
                aSemaphore.release();

            }
        }
    }

}
