package com.lisong.learn.core.concurrency.simulation;

import com.lisong.learn.core.concurrency.common.Worker;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.lisong.learn.core.util.Print.print;

public class SteelWorker extends Worker<House> {

    @Override
    protected void doJob() throws InterruptedException {
        print(this + " lading steel for House " + assembler.getItem().getId());
        TimeUnit.MILLISECONDS.sleep(new Random().nextInt(200));
        assembler.getItem().doneSteel();
    }
}