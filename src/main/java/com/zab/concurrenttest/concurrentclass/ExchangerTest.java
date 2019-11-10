package com.zab.concurrenttest.concurrentclass;

import java.util.concurrent.Exchanger;

/**
 * 交换器
 *
 * @author zab
 * @date 2019-11-10 12:00
 */
public class ExchangerTest {
    String s1 = "s1";
    String s2 = "s2";
    Exchanger<String> exchanger = new Exchanger<>();

    public void f1() {
        try {
            s1 = exchanger.exchange(s1);
            System.out.println("f1----" + s1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void f2() {
        try {
            Thread.sleep(1000);
            s2 = exchanger.exchange(s2);
            System.out.println("f2----" + s2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExchangerTest et = new ExchangerTest();
        new Thread(et::f2).start();
        new Thread(et::f1).start();
    }
}
