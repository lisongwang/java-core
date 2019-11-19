package com.lisong.learn.core.type;

import static com.lisong.learn.core.util.Print.print;
import static com.lisong.learn.core.util.Print.printnb;

public class Exercise21 {

    private static void consume(IService service) {

        service.doSomething();
        service.doSomethingElse("haha");
    }
    public static void main(String[] args) {

        consume(new ServiceProxy(new ServiceImpl()));
    }
}

interface IService {
    void doSomething();
    void doSomethingElse(String s);
}

class ServiceImpl implements IService {
    @Override
    public void doSomething() {
        print("Doing Something...");
        for(int i = 0; i < 100; i++) {
           printnb(i + " ");
        }
        print();
    }

    @Override
    public void doSomethingElse(String s) {
        print("Doing SomethingElse " + s);
        for(int i = 0; i < 1000; i++) {
            printnb(i + " ");
        }
        print();
    }
}

class ServiceProxy implements IService {
    private IService realObj;
    ServiceProxy(IService proxied) { this.realObj = proxied; }
    @Override
    public void doSomething() {
        print("Begin doSomething...");
        long start = System.currentTimeMillis();
        realObj.doSomething();
        print("End doSomething...");
        long end = System.currentTimeMillis();
        print("Time consumed during doSomething is " + (end - start) + " 毫秒");
    }

    @Override
    public void doSomethingElse(String s) {
        print("Begin doSomethingElse...");
        long start = System.currentTimeMillis();
        realObj.doSomethingElse(s);
        print("End doSomethingElse...");
        long end = System.currentTimeMillis();
        print("Time consumed during doSomethingElse is " + (end - start) + " 毫秒");
    }
}