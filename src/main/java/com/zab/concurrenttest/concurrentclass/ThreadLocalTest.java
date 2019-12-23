package com.zab.concurrenttest.concurrentclass;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalTest {
    ThreadLocal threadLocal = new ThreadLocal();
    Map<String, String> map = new HashMap<>(16);
    public static final String KEY = "key";
    String s1 = "dog";
    String s2 = "cat";

    public void f1() {
        threadLocal.set(s1);
        System.out.println("f1通过threadLocal获得的字符串是：" + threadLocal.get());
        map.put(KEY, s1);
        System.out.println("f1通过map获得的字符串是：" + threadLocal.get());
    }

    public void f2() {
        System.out.println("f2通过threadLocal获得的字符串是：" + threadLocal.get());
        System.out.println("f2通过map获得的字符串是：" + map.get(KEY));
        threadLocal.set(s2);
        map.put(KEY, s2);
        System.out.println("f2通过threadLocal获得的字符串是：" + threadLocal.get());
        System.out.println("f2通过map获得的字符串是：" + map.get(KEY));
        threadLocal.set(s1);
        System.out.println(threadLocal.get());
    }

    public static void main(String[] args) throws Exception {

        ThreadLocalTest tlt = new ThreadLocalTest();
        tlt.threadLocal.set("111");

        //new Thread(tlt::f1).start();
        Thread.sleep(2000);
        System.out.println("-----------------------------------------");
        //new Thread(tlt::f2).start();

    }
}
