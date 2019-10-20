package com.zab.concurrenttest.threadcommunication;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * 实现一个容器，提供add，size方法
 * 两个线程，线程一添加十个元素到容器中，线程二监控元素的个数，当个数到5时，线程二给出提示并结束
 *
 * @author zab
 * @date 2019-10-20 21:54
 */
public class ThreadCommunicationTest1 {

    private volatile MyContainer1 myContainer1 = new MyContainer1();

    public void f1() {
        synchronized (this) {

            for (int i = 1; i <= 10; i++) {
                myContainer1.add("test");
                System.out.println("add" + i);
                if(i == 5){
                    this.notify();
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    public void f2() {
        synchronized (this) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("size等于5啦！！！");
            this.notify();
        }
    }


    public static void main(String[] args) {
        ThreadCommunicationTest1 t = new ThreadCommunicationTest1();
        new Thread(t::f2).start();
        new Thread(t::f1).start();
    }
}

class MyContainer1 {
    List<String> list = new LinkedList<>();

    public void add(String s) {
        list.add(s);
    }

    public int size() {
        return list.size();
    }

}
