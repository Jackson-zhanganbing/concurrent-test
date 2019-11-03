package com.zab.concurrenttest.volatiletest;

public class ByteCodeTest {
    private static volatile int i = 0;

    public static void main(String[] args) {
        i++;
        System.out.println(i);
    }
}
