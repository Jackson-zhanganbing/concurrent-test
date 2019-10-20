package com.zab.concurrenttest.a1b2c3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目：两个线程，依次输出ababab...
 *
 * @author zab
 * @date 2019-10-16 22:13
 */
public class Test2 {
    static Lock lock = new ReentrantLock();
    static Condition aCon = lock.newCondition();
    static Condition bCon = lock.newCondition();

    public void m() {
        try {
            lock.lock();
            while (true) {
                System.out.print("a");
                bCon.signal();
                try {
                    aCon.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public void f() {
        try {
            lock.lock();
            while (true) {
                try {
                    bCon.await();
                    System.out.print("b");
                    aCon.signal();
                } catch (Exception e) {
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Test2 test2 = new Test2();
        new Thread(test2::f).start();
        new Thread(test2::m).start();
    }


}
