package com.lisong.learn.core.concurrency.simulation;

import com.lisong.learn.core.concurrency.common.*;
import com.lisong.learn.core.util.Factory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static com.lisong.learn.core.util.Print.print;

public class CarBuilder {

    public static void main(String[] args) throws Exception {
        CarAssemblyLine cal = new CarAssemblyLine();
        if(args.length > 0)
            TimeUnit.SECONDS.sleep(Integer.parseInt(args[0]));
        else {
            print("Press 'Enter' ");
            System.in.read();
        }
        cal.stop();
    }
}

class Car {
    private final int id;
    public Car(int id) { this.id = id; }
    public Car() { this(-1); }

    public synchronized int getId() {
        return id;
    }

    private boolean engine = false, driveTrain = false, wheels = false,
            exhaust = false, body = false, fenders = false;

    public synchronized void addEngine() {
        this.engine = true;
    }

    public synchronized void addDriveTrain() {
        this.driveTrain = true;
    }

    public synchronized void addWheels() {
        this.wheels = true;
    }

    public synchronized void addExhaust() {
        this.exhaust = true;
    }

    public synchronized void addBody() {
        this.body = true;
    }

    public synchronized void addFenders() {
        this.fenders = true;
    }

    @Override
    public synchronized String toString() {
        return "Car " + id + " [engine=" + engine + " driveTrain=" + driveTrain
                + " wheels=" + wheels + " exhaust=" + exhaust +
                " body=" + body + " fenders=" + fenders + "]";
    }
}

class CarQueue extends LinkedBlockingQueue<Car> {}

class CarFactory implements Factory<Car> {
    private AtomicInteger carID = new AtomicInteger(0);

    @Override
    public Car create() {
        return new Car(carID.getAndAdd(1));
    }
}

class ChassisBuilder extends Builder<Car> {

    public ChassisBuilder() {
        bindFactory(new CarFactory());
        setBuildInterval(200, TimeUnit.MILLISECONDS);
    }

    @Override
    public String toString() {
        return "ChassisBuilder";
    }
}

class CarReporter extends Reporter<Car> {

    @Override
    public String toString() {
        return "CarReporter";
    }
}

class CarAssembler extends Assembler<Car> {

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("CarAssembler for [");
        for(Class<? extends Worker> rt : workers)
            sb.append(rt.getSimpleName()).append(",");
        return  sb.substring(0, sb.length()-1) + "]";
    }
}

@SuppressWarnings("unchecked")
class CarAssemblyLine {
    private Workshop<Car> carReporter = new CarReporter();
    private Workshop<Car> chassisBuilder = new ChassisBuilder();
    private Workshop<Car> assemblyPhase1;
    private Workshop<Car> assemblyPhase2;
    {
        assemblyPhase1 = new CarAssembler().bindWorkers(EngineRobot.class, DriveTrainRobot.class, WheelsRobot.class);
        assemblyPhase2 = new CarAssembler().bindWorkers(ExhaustRobot.class, BodyRobot.class, FendersRobot.class);
    }

    public CarAssemblyLine() {
        CarQueue carQueue = new CarQueue();
        CarQueue phase1Queue = new CarQueue();
        CarQueue phase2Queue = new CarQueue();
        chassisBuilder.bindOutQueue(carQueue).start();
        assemblyPhase1.bindInQueue(carQueue).bindOutQueue(phase1Queue).start();
        assemblyPhase2.bindInQueue(phase1Queue).bindOutQueue(phase2Queue).start();
        carReporter.bindInQueue(phase2Queue).start();
    }

    public void stop() {
        chassisBuilder.stop();
        assemblyPhase1.stop();
        assemblyPhase2.stop();
        carReporter.stop();
    }
}