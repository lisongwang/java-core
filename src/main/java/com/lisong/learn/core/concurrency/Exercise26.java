package com.lisong.learn.core.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.lisong.learn.core.util.Print.print;
import static com.lisong.learn.core.util.Print.printnb;

/**
 * Combine exercise 25, exercise 26.
 */
public class Exercise26 {
    public static void main(String[] args) { new Restaurant(); }
}

class Meal {
    private final int orderNum;
    Meal(int orderNum) {
        this.orderNum = orderNum;
    }

    @Override
    public String toString() {
        return "Meal " + orderNum;
    }
}

class WaitPerson implements Runnable {
    private final Restaurant restaurant;
    WaitPerson(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                synchronized (this) {
                    while(restaurant.meal == null) {
                        wait();
                    }
                }
                print("WaitPerson got " + restaurant.meal);
                synchronized (restaurant.chef) {
                    restaurant.meal = null;
                    restaurant.chef.notifyAll();
                }
                print("WaitPerson delivering meal");
                synchronized (restaurant.busBoy) {
                    restaurant.mealDelivered = true;
                    restaurant.busBoy.notifyAll();
                }
            }
        }catch (InterruptedException e) {
            print("WaitPerson interrupted");
        }
    }
}

class Chef implements Runnable {
    private int count = 0;
    private final Restaurant restaurant;
    Chef(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                synchronized (this) {
                    while(restaurant.meal != null) {
                        wait();
                    }
                }
                if(++count == 10) {
                    print("Out of food, closing");
                    restaurant.es.shutdownNow();
                    return;
                }
                printnb("Order up! ");
                synchronized (restaurant.waitPerson) {
                    restaurant.meal = new Meal(count);
                    restaurant.waitPerson.notifyAll();
                }
                TimeUnit.MILLISECONDS.sleep(100);
            }
        }catch (InterruptedException e) {
            print("Chef interrupted");
        }
    }
}

class BusBoy implements Runnable {
    private final Restaurant restaurant;
    BusBoy(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                synchronized (this) {
                    while(!restaurant.mealDelivered) {
                        wait();
                    }
                    print("BusBoy doing cleanup now");
                    TimeUnit.MILLISECONDS.sleep(50);
                    restaurant.mealDelivered = false;
                }
            }
        }catch (InterruptedException e) {
            print("BusBoy interrupted");
        }
    }
}

class Restaurant {
    Meal meal = null;
    boolean mealDelivered = false;
    final WaitPerson waitPerson = new WaitPerson(this);
    final Chef chef = new Chef(this);
    final BusBoy busBoy = new BusBoy(this);
    ExecutorService es = Executors.newCachedThreadPool();

    Restaurant() {
        es.execute(waitPerson);
        es.execute(chef);
        es.execute(busBoy);
    }
}