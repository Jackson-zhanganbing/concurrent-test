package com.zab.concurrenttest.threadcommunication;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 实现一个容器，提供add，size方法
 * 两个线程，线程一添加十个元素到容器中，线程二监控元素的个数，当个数到5时，线程二给出提示并结束
 *
 * @author zab
 * @date 2019-10-20 21:54
 */
public class ThreadCommunicationTest2 {

    private volatile MyContainer2 myContainer2 = new MyContainer2();
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();


    public void f1() {

        lock.lock();
        for (int i = 1; i <= 10; i++) {
            System.out.println("add" + i);
            if (i == 5) {
                c2.signal();
                try {
                    c1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        lock.unlock();

    }

    public void f2() {
        lock.lock();
        try {
            c2.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("size等于5啦！！！");
        c1.signal();
        lock.unlock();
    }


    public static void main(String[] args) {
        ThreadCommunicationTest2 t = new ThreadCommunicationTest2();
        new Thread(t::f2).start();
        new Thread(t::f1).start();
    }
}

class MyContainer2 {
    List<String> list = new LinkedList<>();

    public void add(String s) {
        list.add(s);
    }

    public int size() {
        return list.size();
    }

}
