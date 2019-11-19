package com.lisong.learn.core.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.lisong.learn.core.util.Print.print;
import static com.lisong.learn.core.util.Print.printnb;

/**
 * Lock and Condition version of Restaurant.
 */
public class Exercise27 {
    public static void main(String[] args) { new Restaurant2(); }
}

class WaitPerson2 implements Runnable {
    private final Restaurant2 restaurant;
    WaitPerson2(Restaurant2 restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                restaurant.waitPersonLock.lock();
                try {
                    while(restaurant.meal == null) {
                        restaurant.waitPersonCondition.await();
                    }
                }finally {
                    restaurant.waitPersonLock.unlock();
                }
                print("WaitPerson got " + restaurant.meal);
                restaurant.chefLock.lock();
                try {
                    restaurant.meal = null;
                    restaurant.chefCondition.signalAll();
                }finally {
                    restaurant.chefLock.unlock();
                }
                print("WaitPerson delivering meal");
                restaurant.busBoyLock.lock();
                try {
                    restaurant.mealDelivered = true;
                    restaurant.busBoyCondition.signalAll();
                }finally {
                    restaurant.busBoyLock.unlock();
                }
            }
        }catch (InterruptedException e) {
            print("WaitPerson interrupted");
        }
    }
}

class Chef2 implements Runnable {
    private int count = 0;
    private final Restaurant2 restaurant;
    Chef2(Restaurant2 restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                restaurant.chefLock.lock();
                try {
                    while(restaurant.meal != null) {
                        restaurant.chefCondition.await();
                    }
                }finally {
                    restaurant.chefLock.unlock();
                }
                if(++count == 10) {
                    print("Out of food, closing");
                    restaurant.es.shutdownNow();
                    return;
                }
                printnb("Order up! ");
                restaurant.waitPersonLock.lock();
                try {
                    restaurant.meal = new Meal(count);
                    restaurant.waitPersonCondition.signalAll();
                }finally {
                    restaurant.waitPersonLock.unlock();
                }
                TimeUnit.MILLISECONDS.sleep(100);
            }
        }catch (InterruptedException e) {
            print("Chef interrupted");
        }
    }
}

class BusBoy2 implements Runnable {
    private final Restaurant2 restaurant;
    BusBoy2(Restaurant2 restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                restaurant.busBoyLock.lock();
                try {
                    while(!restaurant.mealDelivered) {
                        restaurant.busBoyCondition.await();
                    }
                    print("BusBoy doing cleanup now");
                    TimeUnit.MILLISECONDS.sleep(50);
                    restaurant.mealDelivered = false;
                }finally {
                    restaurant.busBoyLock.unlock();
                }
            }
        }catch (InterruptedException e) {
            print("BusBoy interrupted");
        }
    }
}

class Restaurant2 {
    final Lock chefLock = new ReentrantLock();
    final Condition chefCondition = chefLock.newCondition();
    final Lock waitPersonLock = new ReentrantLock();
    final Condition waitPersonCondition = waitPersonLock.newCondition();
    final Lock busBoyLock = new ReentrantLock();
    Condition busBoyCondition = busBoyLock.newCondition();
    Meal meal = null;
    boolean mealDelivered = false;
    final WaitPerson2 waitPerson = new WaitPerson2(this);
    final Chef2 chef = new Chef2(this);
    final BusBoy2 busBoy = new BusBoy2(this);
    ExecutorService es = Executors.newCachedThreadPool();

    Restaurant2() {
        es.execute(waitPerson);
        es.execute(chef);
        es.execute(busBoy);
    }
}