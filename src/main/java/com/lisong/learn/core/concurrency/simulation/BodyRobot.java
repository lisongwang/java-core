package com.lisong.learn.core.concurrency.simulation;

import com.lisong.learn.core.concurrency.common.Worker;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.lisong.learn.core.util.Print.print;

public class BodyRobot extends Worker<Car> {

    @Override
    protected void doJob() throws InterruptedException {
        print(this + " installing body for Car " + assembler.getItem().getId());
        TimeUnit.MILLISECONDS.sleep(new Random().nextInt(400));
        assembler.getItem().addBody();
    }
}