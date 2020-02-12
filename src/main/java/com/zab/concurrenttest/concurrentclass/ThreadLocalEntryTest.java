package com.zab.concurrenttest.concurrentclass;

/**
 * 测试ThreadLocal中Entry为什么是数组存储的？
 *
 * @author zab
 * @date 2020-02-03 19:00
 */
public class ThreadLocalEntryTest {
    ThreadLocal t1 = new ThreadLocal();
    ThreadLocal t2 = new ThreadLocal();

    public static void main(String[] args) {
        ThreadLocal t1 = new ThreadLocal();
        ThreadLocal t2 = new ThreadLocal();
        t1.set("111");
        t2.set("111");
        System.out.println(t1.get());
        System.out.println(t2.get());
        System.out.println(4>>>1);
    }
}
