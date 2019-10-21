package com.zab.concurrenttest.producerandconsumer;

import java.util.LinkedList;

/**
 * 写一个固定容量的同步容器，拥有put和get方法，以及getCount方法，能够支持两个生产者线程和十个消费者线程
 *
 * @author zab
 * @date 2019-10-21 21:00
 */
public class ProducerAndConsumerTest {

    Object lock = new Object();
    MyContainer myContainer = new MyContainer();

    public void t1() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            myContainer.put("馒头");
            System.out.println("生产者生产了一个馒头，现在还有：" + myContainer.getCount());

        }

    }

    public void t2() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            myContainer.get();
            System.out.println("消费者消费了一个馒头，现在还有：" + myContainer.getCount());

        }

    }

    public static void main(String[] args) {
        ProducerAndConsumerTest pac = new ProducerAndConsumerTest();
        for (int i = 0; i < 2; i++) {
            new Thread(pac::t1, "生产者").start();
        }
        for (int i = 0; i < 10; i++) {
            new Thread(pac::t2, "消费者").start();
        }
    }

}

class MyContainer<T> {

    LinkedList<T> list = new LinkedList<T>();
    private static final int MAX = 10;
    private volatile int count = 0;

    public synchronized boolean put(T t) {
        while (MAX == list.size()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.add(t);
        count++;
        notifyAll();
        return true;

    }

    public synchronized void get() {
        while (count == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count--;
        list.removeFirst();
        notifyAll();
    }

    public synchronized int getCount() {
        return count;
    }

}
