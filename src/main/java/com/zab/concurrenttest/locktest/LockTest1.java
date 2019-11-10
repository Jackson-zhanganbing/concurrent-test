package com.zab.concurrenttest.locktest;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest1 {

    static Lock lock = new ReentrantLock(true);
    static Condition condition1 = lock.newCondition();
    static Condition condition2 = lock.newCondition();

    static volatile int i = 0;

    public void f1(){
        try {
            lock.lock();
            while (true) {
                while (i < 10) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i++;
                    condition2.signal();
                    System.out.println("生产了"+i);
                }
                try {
                    condition1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }finally {

            lock.unlock();
        }
    }
    public void f2(){
        try{
            lock.lock();
            while(true){
                try {
                    condition2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (i>1){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i--;
                    condition1.signal();
                    System.out.println("消费了"+i);
                }


            }
        }finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) {
        LockTest1 lockTest1 = new LockTest1();
        new Thread(lockTest1::f2).start();
        new Thread(lockTest1::f1).start();
    }


}
