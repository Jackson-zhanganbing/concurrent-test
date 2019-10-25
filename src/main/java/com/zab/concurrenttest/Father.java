package com.zab.concurrenttest;

public class Father {
    protected synchronized void f() {
        System.out.println("我是父亲f方法");
    }

    public static void main(String[] args) {
        Son son = new Son();
        son.f();
    }
}

class Son extends Father{
    @Override
    public synchronized void f(){
        super.f();
        System.out.println("我是儿子f方法");
    }
}
