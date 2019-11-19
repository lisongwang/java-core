package com.lisong.learn.core.concurrency.simulation;

import com.lisong.learn.core.concurrency.common.*;
import com.lisong.learn.core.util.Factory;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static com.lisong.learn.core.util.Print.print;

public class HouseBuilder {

    public static void main(String[] args) throws Exception {
        HouseProject hp = new HouseProject();
        if(args.length > 0)
            TimeUnit.SECONDS.sleep(Integer.parseInt(args[0]));
        else {
            print("Press 'Enter' ");
            System.in.read();
        }
        hp.stop();
    }
}

class House {
    private final int id;
    public House(int id) {
        this.id = id;
    }
    public House() { this(-1); }

    public synchronized int getId() {
        return id;
    }

    private boolean footing = false, steel = false, concreteForms = false,
            concreteFoundation = false, plumbing = false, concreteSlab = false, framing = false;

    public synchronized void doneFooting() {
        this.footing = true;
    }

    public synchronized void doneSteel() {
        this.steel = true;
    }

    public synchronized void doneConcreteForms() {
        this.concreteForms = true;
    }

    public synchronized void doneConcreteFoundation() {
        this.concreteFoundation = true;
    }

    public synchronized void donePlumbing() {
        this.plumbing = true;
    }

    public synchronized void doneConcreteSlab() {
        this.concreteSlab = true;
    }

    public synchronized void doneFraming() {
        this.framing = true;
    }

    @Override
    public synchronized String toString() {
        return "House " + id + " [footing=" + footing + " steel=" + steel
                + " concreteForms=" + concreteForms + " concreteFoundation=" + concreteFoundation +
                " plumbing=" + plumbing + " concreteSlab=" + concreteSlab + " framing=" + framing + "]";
    }
}

class HouseQueue extends LinkedBlockingQueue<House> {}

class HouseFactory implements Factory<House> {
    private AtomicInteger houseID = new AtomicInteger(0);
    @Override
    public House create() {
        return new House(houseID.getAndAdd(1));
    }
}

class HouseDraft extends Builder<House> {

    public HouseDraft() {
        bindFactory(new HouseFactory());
        setBuildInterval(800, TimeUnit.MILLISECONDS);
    }

    @Override
    public String toString() {
        return "HouseDraft";
    }
}

class HouseReporter extends Reporter<House> {

    @Override
    public String toString() {
        return "HouseReporter";
    }
}

class Footings extends SimpleWorkshop<House> {

    @Override
    protected void process() throws InterruptedException {
        print(this + " dug footings for House " + t.getId());
        TimeUnit.MILLISECONDS.sleep(new Random().nextInt(400));
        t.doneFooting();
    }

    @Override
    public String toString() {
        return "Footings";
    }
}

class HouseAssembler extends Assembler<House> {

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("HouseAssembler for [");
        for(Class<? extends Worker> rt : workers)
            sb.append(rt.getSimpleName()).append(",");
        return  sb.substring(0, sb.length()-1) + "]";
    }
}

class Foundation extends SimpleWorkshop<House> {

    @Override
    protected void process() throws InterruptedException {
        print(this + " pouring concrete foundation for House " + t.getId());
        TimeUnit.MILLISECONDS.sleep(new Random().nextInt(450));
        t.doneConcreteFoundation();
    }

    @Override
    public String toString() {
        return "Foundation";
    }
}

class Plumbing extends SimpleWorkshop<House> {

    @Override
    protected void process() throws InterruptedException {
        print(this + " plumbing for House " + t.getId());
        TimeUnit.MILLISECONDS.sleep(new Random().nextInt(100));
        t.donePlumbing();
    }

    @Override
    public String toString() {
        return "Plumbing";
    }
}

class Slab extends SimpleWorkshop<House> {

    @Override
    protected void process() throws InterruptedException {
        print(this + " pouring concrete slab for House " + t.getId());
        TimeUnit.MILLISECONDS.sleep(new Random().nextInt(250));
        t.doneConcreteSlab();
    }

    @Override
    public String toString() {
        return "Slab";
    }
}

class Framing extends SimpleWorkshop<House> {

    @Override
    protected void process() throws InterruptedException {
        print(this + " framing for House " + t.getId());
        TimeUnit.MILLISECONDS.sleep(new Random().nextInt(350));
        t.doneFraming();
    }

    @Override
    public String toString() {
        return "Framing";
    }
}

@SuppressWarnings("unchecked")
class HouseProject {

    private Workshop<House> houseReporter = new HouseReporter();
    private Workshop<House> houseDraft = new HouseDraft();
    private Workshop<House> footings = new Footings();
    private Workshop<House> foundation = new Foundation();
    private Workshop<House> plumbing = new Plumbing();
    private Workshop<House> slab = new Slab();
    private Workshop<House> framing = new Framing();
    private Workshop<House> houseAssembler;
    {
        houseAssembler = new HouseAssembler().bindWorkers(SteelWorker.class, ConcreteFormsWorker.class);
    }

    public HouseProject() {
        HouseQueue houseQueue = new HouseQueue();
        HouseQueue footingQueue = new HouseQueue();
        HouseQueue assemblyQueue = new HouseQueue();
        HouseQueue foundationQueue = new HouseQueue();
        HouseQueue plumbingQueue = new HouseQueue();
        HouseQueue slabQueue = new HouseQueue();
        HouseQueue framingQueue = new HouseQueue();

        houseDraft.bindOutQueue(houseQueue).start();
        footings.bindInQueue(houseQueue).bindOutQueue(footingQueue).start();
        houseAssembler.bindInQueue(footingQueue).bindOutQueue(assemblyQueue).start();
        foundation.bindInQueue(assemblyQueue).bindOutQueue(foundationQueue).start();
        plumbing.bindInQueue(foundationQueue).bindOutQueue(plumbingQueue).start();
        slab.bindInQueue(plumbingQueue).bindOutQueue(slabQueue).start();
        framing.bindInQueue(slabQueue).bindOutQueue(framingQueue).start();
        houseReporter.bindInQueue(framingQueue).start();
    }

    public void stop() {
        houseDraft.stop();
        footings.stop();
        houseAssembler.stop();
        foundation.stop();
        plumbing.stop();
        slab.stop();
        framing.stop();
        houseReporter.stop();
    }
}