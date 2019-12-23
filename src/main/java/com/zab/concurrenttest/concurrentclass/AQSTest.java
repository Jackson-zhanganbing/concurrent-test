package com.zab.concurrenttest.concurrentclass;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AQSTest {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        lock.lock();
        AbstractQueuedSynchronizer aqs = new AbstractQueuedSynchronizer() {
            @Override
            protected boolean tryAcquire(int arg) {
                return super.tryAcquire(arg);
            }

            @Override
            protected boolean tryRelease(int arg) {
                return super.tryRelease(arg);
            }

            @Override
            protected int tryAcquireShared(int arg) {
                return super.tryAcquireShared(arg);
            }

            @Override
            protected boolean tryReleaseShared(int arg) {
                return super.tryReleaseShared(arg);
            }

            @Override
            protected boolean isHeldExclusively() {
                return super.isHeldExclusively();
            }

            @Override
            public String toString() {
                return super.toString();
            }
        };

    }
}
