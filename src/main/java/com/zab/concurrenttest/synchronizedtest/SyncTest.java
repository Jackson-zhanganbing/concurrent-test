package com.zab.concurrenttest.synchronizedtest;

import java.util.concurrent.Semaphore;

/**
 * synchronized测试
 *
 * @author zab
 * @date 2019-10-25 23:21
 */
public class SyncTest {
    static Semaphore semaphore1 = new Semaphore(0);
    static Semaphore semaphore2 = new Semaphore(0);
    int i = 0;
    public synchronized void f1(){
        for (int j = 0; j < 100000; j++) {
            i++;
        }
        semaphore1.release();
    }
    public synchronized void f2(){
        for (int j = 0; j < 100000; j++) {
            i++;
        }
        semaphore2.release();
    }

    public static void main(String[] args) {

        SyncTest t = new SyncTest();
        new Thread(t::f1).start();
        new Thread(t::f2).start();
        try {
            semaphore1.acquire();
            semaphore2.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(t.i);
    }
}
