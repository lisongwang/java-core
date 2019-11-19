package com.lisong.learn.core.concurrency;

import java.util.concurrent.*;
import java.util.function.Consumer;

import static com.lisong.learn.core.util.Print.print;

public class Exercise42 {

    public static void main(String[] args) throws Exception {
        ActiveCar car = new ActiveCar();
        ExecutorService es = Executors.newCachedThreadPool();
        for(int i = 0; i < 10; i++) {
            es.execute(()->{
                try {
                    while(!Thread.interrupted()) {
                        car.submitWaxJob(car.new WaxJob());
                        TimeUnit.MILLISECONDS.sleep(400);
                    }
                }catch (InterruptedException e) {
                    //normal exit
                }
            });
        }

        TimeUnit.SECONDS.sleep(1);
        es.shutdownNow();
        car.shutdown((r)->print(r.show()));
    }
}

class ActiveCar {

    private ExecutorService es = Executors.newSingleThreadExecutor();

    private CountDownLatch cdl = new CountDownLatch(Integer.MAX_VALUE);

    private boolean waxOn = false;

    private void countJob() {
        cdl.countDown();
    }

    private boolean isWaxed() {
        return waxOn;
    }

    private void waxOn() {
        waxOn = true;
        print("Wax On! ");
    }

    private void waxOff() {
        waxOn = false;
        print("Wax Off! ");
    }

    private void waxed() throws InterruptedException {
        if(isWaxed())
            throw new RuntimeException("Can not wax for a waxed car");
        waxOn();
        TimeUnit.MILLISECONDS.sleep(200);
    }

    private void buffered() throws InterruptedException {
        if(!isWaxed())
            throw new RuntimeException("Can not buffer for an unwaxed car");
        waxOff();
        TimeUnit.MILLISECONDS.sleep(200);
    }

    public Future<Void> submitWaxJob(WaxJob job) {
        return es.submit(job);
    }

    public void shutdown() { es.shutdown(); }

    public void shutdown(Consumer<Report> shutdownHook) throws Exception {
        es.shutdown();
        Report report = new Report();
        if(es.awaitTermination(30, TimeUnit.SECONDS)) {
            shutdownHook.accept(report.append("Total waxJob: " + (Integer.MAX_VALUE - cdl.getCount())));
        }
        else {
            shutdownHook.accept(report.append("Not yet complete!"));
        }
    }

    public class WaxJob implements Callable<Void> {

        @Override
        public Void call() throws Exception {
            countJob();
            waxed();
            buffered();
            return null;
        }
    }

    public class Report {
        private String content = "";

        public Report append(String content) {
            this.content += content;
            return this;
        }

        public String show() {
            return "Report: " + content;
        }
    }
}