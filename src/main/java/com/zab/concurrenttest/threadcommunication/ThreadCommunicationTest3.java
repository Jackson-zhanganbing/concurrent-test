package com.zab.concurrenttest.threadcommunication;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 实现一个容器，提供add，size方法
 * 两个线程，线程一添加十个元素到容器中，线程二监控元素的个数，当个数到5时，线程二给出提示并结束
 *
 * @author zab
 * @date 2019-10-20 21:54
 */
public class ThreadCommunicationTest3 {

    private volatile MyContainer3 myContainer3 = new MyContainer3();
    Thread t1 = null;
    Thread t2 = null;

    public void f1() {

        for (int i = 1; i <= 10; i++) {
            myContainer3.add("test");
            System.out.println("add" + i);
            if (i == 5) {
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        }

    }

    public void f2() {
        LockSupport.park();
        System.out.println("size等于5啦！！！");
        LockSupport.unpark(t1);
    }


    public static void main(String[] args) {
        ThreadCommunicationTest3 t = new ThreadCommunicationTest3();
        t.t2 = new Thread(t::f2);
        t.t1 = new Thread(t::f1);
        t.t2.start();
        t.t1.start();
    }
}

class MyContainer3 {
    List<String> list = new LinkedList<>();

    public void add(String s) {
        list.add(s);
    }

    public int size() {
        return list.size();
    }

}
