package com.zab.concurrenttest.locktest;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest1 {
    static Lock lock = new ReentrantLock(true);
    static int i1 = 0;
    static int i2 = 0;

    public void f1(){
        lock.lock();
    }

    public static void main(String[] args) {

    }
}
