package com.lisong.learn.core.concurrency;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static com.lisong.learn.core.util.Print.print;

public class Exercise5 implements Callable<Integer> {

    private int result;
    private int n;
    public Exercise5(int n) {
        this.n = n;
    }
    @Override
    public Integer call() {
        for(int i = 0 ; i < n; i++)
            result += f(i);
        return result;
    }
    private int f(int i) {
        if(i < 2)
            return 1;
        else
            return f(i-2) + f(i-1);
    }

    public static void main(String[] args) {
        ExecutorService es = Executors.newCachedThreadPool();
        List<Callable<Integer>> clist = new ArrayList<>(Arrays.asList(
                new Exercise5(2),
                new Exercise5(10),
                new Exercise5(20),
                new Exercise5(30),
                new Exercise5(40)));
        try {
            List<Future<Integer>> rlist = es.invokeAll(clist);
            for(Future<Integer> result : rlist)
                print(result.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
        es.shutdown();
    }
}