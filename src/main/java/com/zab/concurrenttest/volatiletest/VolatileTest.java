package com.zab.concurrenttest.volatiletest;

import java.util.concurrent.Semaphore;

public class VolatileTest {
    static Semaphore semaphore = new Semaphore(0);

    public static void main(String[] args) {

        VolatileTest t = new VolatileTest();

        for (int i = 0; i < 2000; i++) {
            new Thread(t::f).start();
        }
        semaphore.release(2000);

    }

    public void f(){
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Singleton.getInstance());
    }

}

class Singleton {
    private static Singleton singleton;
    private Singleton(){

    }
    public static Singleton getInstance(){
        if(singleton == null){
            synchronized (Singleton.class){
                if(singleton == null){
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
