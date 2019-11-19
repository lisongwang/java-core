package com.lisong.learn.core.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.lisong.learn.core.util.Print.print;

public class Exercise2 implements Runnable {
    private static int count = 0;
    private final int id = count++;
    private StringBuilder result = new StringBuilder();
    private boolean complete = false;
    private int n;
    public Exercise2(int n) {
        this.n = n;
    }
    @Override
    public void run() {
        for(int i = 0 ; i < n; i++)
            result.append(f(i)).append(" ");
        complete = true;
    }
    private int f(int i) {
        if(i < 2)
            return 1;
        else
            return f(i-2) + f(i-1);
    }
    boolean isComplete() { return complete; }
    String getResult() { return result.toString(); }
    @Override
    public String toString() {
        return "Task " + id;
    }

    public static void main(String[] args) {
        List<Exercise2> results = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            Exercise2 exe2 = new Exercise2(30);
            results.add(exe2);
            new Thread(exe2).start();
        }
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(Exercise2 exe2 : results) {
            if(exe2.complete)
                print(exe2.result.toString());
            else
                print("Not complete for " + exe2);
        }
    }
}