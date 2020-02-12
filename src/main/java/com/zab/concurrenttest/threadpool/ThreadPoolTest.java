package com.zab.concurrenttest.threadpool;

import java.util.concurrent.*;

/**
 * 线程池详解
 *
 * @author zab
 * @date 2020-01-21 22:40
 */
public class ThreadPoolTest {
    private static final int CORE_POOL_SIZE = 5;
    private static final int MAX_POOL_SIZE = 8;
    private static final int KEEP_ALIVE_TIME = 60;

    public static void main(String[] args) throws Exception{

        ThreadPoolTest t = new ThreadPoolTest();

        ThreadPoolExecutor e = t.getThreadPool4();

        for (int i = 0; i < 100; i++) {
            e.execute(t.new MyRunnable(i));
        }
        
    }

    private ThreadPoolExecutor getThreadPool0() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                new MyThreadFactory("whatBusiness"),
                new ThreadPoolExecutor.AbortPolicy()
        );

        return threadPoolExecutor;
    }

    private ThreadPoolExecutor getThreadPool1() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                new MyThreadFactory("whatBusiness"),
                new ThreadPoolExecutor.DiscardPolicy()
        );

        return threadPoolExecutor;
    }

    private ThreadPoolExecutor getThreadPool2() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                new MyThreadFactory("whatBusiness"),
                new ThreadPoolExecutor.DiscardOldestPolicy()
        );

        return threadPoolExecutor;
    }

    private ThreadPoolExecutor getThreadPool3() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                new MyThreadFactory("whatBusiness"),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );

        return threadPoolExecutor;
    }

    private ThreadPoolExecutor getThreadPool4() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                new MyThreadFactory("whatBusiness"),
                new MyRejectedExecutionHandler()
        );

        return threadPoolExecutor;
    }

    class MyRejectedExecutionHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println("做自己想要的逻辑");
            System.out.println("核心池大小：" + executor.getCorePoolSize());
        }
    }


    class MyThreadFactory implements ThreadFactory {

        private String threadName;

        public MyThreadFactory(String threadName) {
            this.threadName = threadName;
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setName(threadName+t.getName());
            return t;
        }
    }

    class MyRunnable implements Runnable {

        int count = 0;

        public MyRunnable(int count){
            this.count = count;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread---"+Thread.currentThread().getName()+"----count:"+count);
        }
    }
}
