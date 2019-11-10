package com.zab.concurrenttest.concurrentclass;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 *
* @author zab
* @date 2019-11-09 18:44
*/
public class CyclicBarrierTest {
    public static void main(String[] args) {
        f();
    }

    public static void f(){
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2,()->System.out.println("满二十人，发车"));
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable(){
                @Override
                public void run() {
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    public static void f1(){
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2,()->System.out.println("满二人，发车"));
        for (int i = 0; i < 10; i++) {
            System.out.println("------");
            try {
                cyclicBarrier.await(2, TimeUnit.NANOSECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }
    }
}
