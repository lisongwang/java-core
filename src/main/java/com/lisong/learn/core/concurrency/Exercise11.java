package com.lisong.learn.core.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.lisong.learn.core.util.Print.print;

public class Exercise11 {
    private String text = "good";
    private int num = 100;

    synchronized private void convert() {
        text = "improper";
        Thread.yield();
        text = "good";
        num = -1;
        Thread.yield();
        num = 100;
    }
    synchronized private String getText() { return text; }
    synchronized private int getNum() { return num; }

    static class Mytask implements Runnable {
        Exercise11 exe11;
        Mytask(Exercise11 exe11) {
            this.exe11 = exe11;
        }
        @Override
        public void run() {
            exe11.convert();
            print("text = " + exe11.getText() + "  num = " + exe11.getNum());
        }
    }

    public static void main(String[] args) {
        Exercise11 exe11 = new Exercise11();
        ExecutorService es = Executors.newCachedThreadPool();
        for(int i = 0; i < 30; i++)
            es.execute(new Mytask(exe11));
        es.shutdown();
    }
}