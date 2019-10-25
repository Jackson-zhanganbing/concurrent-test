package com.zab.concurrenttest.producerandconsumer;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 写一个固定容量的同步容器，拥有put和get方法，以及getCount方法，能够支持两个生产者线程和十个消费者线程
 *
 * @author zab
 * @date 2019-10-21 21:00
 */
public class ProducerAndConsumerTest1 {

    Object lock = new Object();
    MyContainer1 myContainer1 = new MyContainer1();

    public void t1() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            myContainer1.put("馒头");

        }

    }

    public void t2() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            myContainer1.get();

        }

    }

    public static void main(String[] args) {
        ProducerAndConsumerTest pac = new ProducerAndConsumerTest();
        for (int i = 0; i < 10; i++) {
            new Thread(pac::t1, "生产者").start();
        }
        for (int i = 0; i < 10; i++) {
            new Thread(pac::t2, "消费者").start();
        }
    }

}


class MyContainer1<T> {

    LinkedList<T> list = new LinkedList<T>();
    private static final int MAX = 10;
    private volatile int count = 0;
    Lock lock = new ReentrantLock();
    Condition putCondition = lock.newCondition();
    Condition getCondition = lock.newCondition();

    public boolean put(T t) {
        while (MAX == list.size()) {
            try {
                putCondition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.add(t);
        count++;
        System.out.println("生产者生产了一个馒头，现在还有：" + getCount());
        getCondition.notify();
        return true;

    }

    public void get() {
        while (count == 0) {
            try {
                getCondition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count--;
        list.removeFirst();
        System.out.println("消费者消费了一个馒头，现在还有：" + getCount());
        putCondition.notify();
    }

    public int getCount() {
        return count;
    }

}
