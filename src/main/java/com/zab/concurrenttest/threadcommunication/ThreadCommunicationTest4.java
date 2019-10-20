package com.zab.concurrenttest.threadcommunication;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.LockSupport;

/**
 * 实现一个容器，提供add，size方法
 * 两个线程，线程一添加十个元素到容器中，线程二监控元素的个数，当个数到5时，线程二给出提示并结束
 *
 * @author zab
 * @date 2019-10-20 21:54
 */
public class ThreadCommunicationTest4 {

    private volatile MyContainer4 myContainer4 = new MyContainer4();
    CountDownLatch latch = new CountDownLatch(5);
    CountDownLatch latch1 = new CountDownLatch(1);

    public void f1() {

        for (int i = 1; i <= 10; i++) {
            System.out.println("add" + i);
            latch.countDown();
            if (i == 5) {
                try {
                    latch1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void f2() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("size等于5啦！！！");
        latch1.countDown();
    }


    public static void main(String[] args) {
        ThreadCommunicationTest4 t = new ThreadCommunicationTest4();
        new Thread(t::f2).start();
        new Thread(t::f1).start();
    }
}

class MyContainer4 {
    List<String> list = new LinkedList<>();

    public void add(String s) {
        list.add(s);
    }

    public int size() {
        return list.size();
    }

}
