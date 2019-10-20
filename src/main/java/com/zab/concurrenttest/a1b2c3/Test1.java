package com.zab.concurrenttest.a1b2c3;
/**
 * 十个线程，线程一输出：0，线程二输出：1，...线程十输出：9
* @author zab
* @date 2019-10-16 22:51
*/
public class Test1 {
    static Thread t1 = new Thread(new One());
    static Thread t2 = new Thread(new Two());
    static Thread t3 = new Thread(new Three());
    static Thread t4 = new Thread(new Four());
    static Thread t5 = new Thread(new Five());
    static Thread t6 = new Thread(new Six());
    static Thread t7 = new Thread(new Seven());
    static Thread t8 = new Thread(new Eight());
    static Thread t9 = new Thread(new Nine());
    static Thread t10 = new Thread(new Ten());

    public static void main(String[] args) {
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        t9.start();
        t10.start();
    }

    static class One implements Runnable{
        @Override
        public void run() {
            System.out.print(0);
            try {
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    static class Two implements Runnable{
        @Override
        public void run() {
            System.out.print(1);
            try {
                t3.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    static class Three implements Runnable{
        @Override
        public void run() {
            System.out.print(2);
            try {
                t4.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    static class Four implements Runnable{
        @Override
        public void run() {
            System.out.print(3);
            try {
                t5.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    static class Five implements Runnable{
        @Override
        public void run() {
            System.out.print(4);
            try {
                t6.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    static class Six implements Runnable{
        @Override
        public void run() {
            System.out.print(5);
            try {
                t7.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    static class Seven implements Runnable{
        @Override
        public void run() {
            System.out.print(6);
            try {
                t8.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    static class Eight implements Runnable{
        @Override
        public void run() {
            System.out.print(7);
            try {
                t9.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    static class Nine implements Runnable{
        @Override
        public void run() {
            System.out.print(8);
        }
    }
    static class Ten implements Runnable{
        @Override
        public void run() {
            System.out.print(9);
        }
    }
}
