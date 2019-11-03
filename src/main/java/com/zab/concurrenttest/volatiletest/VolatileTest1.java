package com.zab.concurrenttest.volatiletest;

public class VolatileTest1 {
    private volatile Integer i = 0;

    /**
     * 改变i的值
     */
    public void f1() {
        while (i < 5) {
            i++;
            System.out.println("改变i的值：" + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 监听i的值
     */
    public void f2() {
        int localVal = i;
        while (i < 5) {
            if (localVal != i) {
                System.out.println("i发生改变：" + i);
                localVal = i;
            }
        }
    }

    public static void main(String[] args) {
        VolatileTest1 v1 = new VolatileTest1();
        new Thread(v1::f1).start();
        new Thread(v1::f2).start();
    }
}
