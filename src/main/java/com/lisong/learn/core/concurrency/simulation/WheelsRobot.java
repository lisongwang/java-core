package com.lisong.learn.core.concurrency.simulation;

import com.lisong.learn.core.concurrency.common.Worker;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.lisong.learn.core.util.Print.print;

public class WheelsRobot extends Worker<Car> {

    @Override
    protected void doJob() throws InterruptedException {
        print(this + " installing wheels for Car " + assembler.getItem().getId());
        TimeUnit.MILLISECONDS.sleep(new Random().nextInt(200));
        assembler.getItem().addWheels();
    }
}