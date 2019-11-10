package com.zab.concurrenttest.concurrentclass;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 *
 * @author zab
 * @date 2019-11-10 11:46
 */
public class ReadWriteLockTest {
    Lock readLock;
    Lock writeLock;
    Lock commonLock;
    static Semaphore readSemaphore = new Semaphore(0);
    static Semaphore writeSemaphore = new Semaphore(0);
    static Semaphore writeSemaphore1 = new Semaphore(0);
    static Semaphore readSemaphore1 = new Semaphore(0);
    ThreadPoolExecutor threadPoolExecutor;

    Map<String, String> map = new HashMap<>();

    public ReadWriteLockTest(Lock readLock, Lock writeLock) {
        this.readLock = readLock;
        this.writeLock = writeLock;
    }

    public static void main(String[] args) throws Exception {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        ReentrantLock commonLock = new ReentrantLock();
        ReadWriteLockTest test = new ReadWriteLockTest(lock.readLock(),lock.writeLock());
        //ReadWriteLockTest test = new ReadWriteLockTest(commonLock, commonLock);

        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            new Thread(test.new WriteOperation()).start();
        }
        writeSemaphore.release(100);
        writeSemaphore1.acquire(100);
        long end = System.currentTimeMillis();

        System.out.println("lock锁写操作耗时：" + (end - start));


        long start1 = System.currentTimeMillis();

        for (int i = 0; i < 100; i++) {
            new Thread(test.new ReadOperation()).start();
        }
        readSemaphore.release(100);
        readSemaphore1.acquire(100);
        long end1 = System.currentTimeMillis();
        System.out.println("lock锁读操作耗时：" + (end1 - start1));

    }

    class WriteOperation implements Runnable {

        @Override
        public void run() {
            writeLock.lock();
            try {
                writeSemaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            writeSemaphore1.release();
            writeLock.unlock();
        }
    }

    class ReadOperation implements Runnable {
        @Override
        public void run() {
            readLock.lock();
            try {
                readSemaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            readSemaphore1.release();
            readLock.unlock();
        }

    }


    public ThreadPoolExecutor getThreadPool() {
        if (threadPoolExecutor == null) {
            synchronized (ReadWriteLockTest.class) {
                if (threadPoolExecutor == null) {
                    ThreadFactory threadFactory = new ThreadFactory() {
                        @Override
                        public Thread newThread(Runnable r) {
                            return new Thread("tls");
                        }
                    };
                    threadPoolExecutor = new ThreadPoolExecutor(
                            100,
                            100,
                            0,
                            TimeUnit.SECONDS,
                            new LinkedBlockingDeque<>(100000),
                            threadFactory,
                            new MyRejectedExecutionHandler()

                    );
                }
            }
        }

        return threadPoolExecutor;
    }

    class MyRejectedExecutionHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            return;
        }
    }

}
