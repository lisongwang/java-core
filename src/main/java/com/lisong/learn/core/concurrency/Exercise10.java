package com.lisong.learn.core.concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static com.lisong.learn.core.util.Print.print;

public class Exercise10 {
    private ExecutorService es = Executors.newCachedThreadPool();
    private Future<Integer> runTask(int n) {
        return es.submit(new Callable<Integer>() {
            @Override
            public Integer call() {
                int result = 0;
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
        });
    }

    public static void main(String[] args) throws Exception {
        Exercise10 exe10 = new Exercise10();
        print(exe10.runTask(10).get());
        print(exe10.runTask(20).get());
        print(exe10.runTask(30).get());
        print(exe10.runTask(40).get());
        exe10.es.shutdown();
    }
}