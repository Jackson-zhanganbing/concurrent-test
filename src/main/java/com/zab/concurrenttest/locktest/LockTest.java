package com.zab.concurrenttest.locktest;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lock测试
 *
 * @author zab
 * @date 2019-11-03 11:22
 */
public class LockTest {
    static Semaphore semaphore1 = new Semaphore(0);
    static Semaphore semaphore2 = new Semaphore(0);
    static Lock lock = new ReentrantLock();
    static int i1 = 0;
    static int i2 = 0;

    public void f1() {
        try {
            lock.lock();
            for (int j = 0; j < 1000000; j++) {
                i1++;
            }
        } finally {
            lock.unlock();
        }

        semaphore1.release();
    }

    public void f2() {
        try {
            lock.lock();
            lock.lock();
            for (int j = 0; j < 1000000; j++) {
                i1++;
            }
        } finally {
            lock.unlock();
        }
        semaphore2.release();
    }

    public void f3() {

        for (int j = 0; j < 1000000; j++) {
            i2++;
        }

        semaphore1.release();
    }
    public void f4() {

        for (int j = 0; j < 1000000; j++) {
            i2++;
        }

        semaphore2.release();
    }


    public static void main(String[] args) {
        LockTest lockTest = new LockTest();
        new Thread(lockTest::f1).start();
        new Thread(lockTest::f2).start();
        new Thread(lockTest::f3).start();
        new Thread(lockTest::f4).start();

        try {
            semaphore1.acquire();
            semaphore1.acquire();
            semaphore2.acquire();
            semaphore2.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(i1);
        System.out.println(i2);
    }


}
