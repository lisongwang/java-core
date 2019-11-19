package com.lisong.learn.core.concurrency.simulation;

import com.lisong.learn.core.concurrency.common.Worker;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.lisong.learn.core.util.Print.print;

public class ExhaustRobot extends Worker<Car> {

    @Override
    protected void doJob() throws InterruptedException {
        print(this + " installing exhaust for Car " + assembler.getItem().getId());
        TimeUnit.MILLISECONDS.sleep(new Random().nextInt(50));
        assembler.getItem().addExhaust();
    }
}