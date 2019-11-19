package com.lisong.learn.core.concurrency.simulation;

import com.lisong.learn.core.enumerated.enums.Course;
import com.lisong.learn.core.enumerated.enums.Food;

import java.util.*;
import java.util.concurrent.*;

import static com.lisong.learn.core.util.Print.print;

public class RestaurantWithQueues {
    public static void main(String[] args) throws Exception {
        Restaurant restaurant = new Restaurant(10, 2, 5);
        if(args.length > 0)
            TimeUnit.SECONDS.sleep(Integer.parseInt(args[0]));
        else {
            print("Press 'Enter' ");
            System.in.read();
        }
        restaurant.close();
    }
}

class OrderTicket {
    private static int count = 0;
    private final int id = count++;
    private Table table;
    private WaitPerson waitPerson;
    private Map<Customer,Food> orderList = new ConcurrentHashMap<>();

    public Table getTable() {
        return table;
    }

    public WaitPerson getWaitPerson() {
        return waitPerson;
    }

    public Map<Customer, Food> getOrderList() {
        return orderList;
    }

    public OrderTicket(Table table, WaitPerson waitPerson) {
        this.table = table;
        this.waitPerson = waitPerson;
    }

    @Override
    public String toString() {
        return "OrderTicket: " + id + " table: " + table +
                " " + orderList + " served by " + waitPerson;
    }
}

class Table {
    private final int id;
    private CyclicBarrier orderBarrier;
    private CyclicBarrier leaveBarrier;
    private Tables tables;
    private WaitPerson waitPerson;
    private OrderTicket orderTicket;

    public Table(int id, Tables tables) {
        this.id = id;
        this.tables = tables;
    }

    public void setWaitPerson(WaitPerson waitPerson) {
        this.waitPerson = waitPerson;
        orderTicket = new OrderTicket(this, waitPerson);
    }

    public void setCustomerNum (int num) {
        orderBarrier = new CyclicBarrier(num, ()->this.waitPerson.placeOrder(orderTicket));
        leaveBarrier = new CyclicBarrier(num, ()->this.tables.releaseTable(this));
    }

    public int getId() {
        return id;
    }

    public void submitOrder(Customer customer, Food food) throws InterruptedException {
        try {
            orderTicket.getOrderList().put(customer, food);
            orderBarrier.await();// wait for all the customers around the table submit their orders
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }

    public void leave() throws InterruptedException {
        try {
            leaveBarrier.await();
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "Table " + id;
    }
}

class Tables {

    private Table[] tables;
    private boolean[] status;
    private Semaphore available;
    public Tables(int num) {
        available = new Semaphore(num);
        tables = new Table[num];
        for(int i = 0; i < num; i++)
            tables[i] = new Table(i, this);
        status = new boolean[num];
        Arrays.fill(status, true);
    }

    public synchronized Table getAvailableTable() throws InterruptedException {
        available.acquire();
        for(int i = 0; i < status.length; i++) {
            if(status[i]) {
                status[i] = false;
                return tables[i];
            }
        }
        throw new RuntimeException("Should not be here!");
    }

    public synchronized void releaseTable(Table table) {
        for(int i = 0; i < status.length; i++) {
            if(i == table.getId() && !status[i]) {
                status[i] = true;
                available.release();
            }
        }
    }
}

class Plate {
    private final OrderTicket orderTicket;
    private final Customer customer;
    private final Food food;

    public Plate(OrderTicket orderTicket, Customer customer, Food food) {
        this.orderTicket = orderTicket;
        this.customer = customer;
        this.food = food;
    }

    public OrderTicket getOrderTicket() {
        return orderTicket;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Food getFood() {
        return food;
    }

    @Override
    public String toString() {
        return food.toString();
    }
}

class Customer implements Runnable {
    private static int count = 0;
    private final int id = count++;
    private Table table;
    private SynchronousQueue<Plate> placeSetting = new SynchronousQueue<>();

    public Customer(Table table) {
        this.table = table;
    }

    public void deliver(Plate plate) throws InterruptedException {
        placeSetting.put(plate);
    }

    @Override
    public void run() {
        for(Course course : Course.values()) {
            Food food = course.randomSelect();
            try {
                table.submitOrder(this, food);
                print(this + " eating " + placeSetting.take());
            }catch (InterruptedException e) {
                print(this + " waiting for " + course + " interrupted");
                break;
            }
        }
        try {
            table.leave();
        } catch (InterruptedException e) {
            print(this + " waiting for leave interrupted");
        }
        print(this + " finished meal, leaving");
    }

    @Override
    public String toString() {
        return "Customer " + id;
    }
}

class WaitPerson implements Runnable {
    private static int count = 0;
    private final int id = count++;
    private Restaurant restaurant;

    public final BlockingQueue<Plate> filledOrders = new LinkedBlockingDeque<>();

    public WaitPerson(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void placeOrder(OrderTicket orderTicket) {
        try {
            restaurant.orders.put(orderTicket);
        } catch (InterruptedException e) {
            print(this + " placeOrder interrupted");
        }
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                Plate p = filledOrders.take();
                print(this + " receiving " + p + " delivering to " +
                        p.getCustomer() + " on table " + p.getOrderTicket().getTable());
                p.getCustomer().deliver(p);
            }
        }catch (InterruptedException e) {
            print(this + " interrupted");
        }
        print(this + " off duty");
    }

    @Override
    public String toString() {
        return "Waitperson " + id + " ";
    }
}

class Chef implements Runnable {
    private static int count = 0;
    private final int id = count++;
    private Restaurant restaurant;
    private Random rand = new Random(87);

    public Chef(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                OrderTicket orderTicket = restaurant.orders.take();
                for(Map.Entry<Customer,Food> entry : orderTicket.getOrderList().entrySet()) {
                    TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
                    Plate plate = new Plate(orderTicket, entry.getKey(), entry.getValue());
                    orderTicket.getWaitPerson().filledOrders.put(plate);
                }
            }
        }catch (InterruptedException e) {
            print(this + " interrupted");
        }
        print(this + " off duty");
    }

    @Override
    public String toString() {
        return "Chef " + id + " ";
    }
}

class Restaurant implements Runnable {
    private final List<WaitPerson> waitPersons = new ArrayList<>();
    private final List<Chef> chefs = new ArrayList<>();
    private final Tables tables;
    private final ExecutorService customerEs = Executors.newCachedThreadPool();
    private final ExecutorService restaurantEs = Executors.newCachedThreadPool();
    private Random rand = new Random(44);
    private volatile boolean closing = false;

    public final BlockingQueue<OrderTicket> orders = new LinkedBlockingDeque<>();

    public Restaurant(int nTables, int nChefs, int nWaitPersons) {
        tables = new Tables(nTables);
        for(int i = 0; i < nChefs; i++) {
            Chef chef = new Chef(this);
            chefs.add(chef);
            restaurantEs.execute(chef);
        }
        for(int i = 0; i < nWaitPersons; i++) {
            WaitPerson waitPerson = new WaitPerson(this);
            waitPersons.add(waitPerson);
            restaurantEs.execute(waitPerson);
        }
        restaurantEs.execute(this);
    }

    public void close() {
        closing = true;
        synchronized (customerEs) {
            customerEs.shutdown();
        }
        try {
            while(!customerEs.awaitTermination(5, TimeUnit.SECONDS)) {
                print("Wait for the last customer leave!");
            }
        } catch (InterruptedException e) {
            print("Interrupted while waiting for all customers to leave");
        }
        print("All customers leave now, we can close our restaurant.");
        restaurantEs.shutdownNow();
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                if(!closing) {
                    synchronized (customerEs) {
                        if(!closing) {
                            Table table = tables.getAvailableTable();
                            WaitPerson wp = waitPersons.get(rand.nextInt(waitPersons.size()));
                            int num = rand.nextInt(6) + 1;
                            table.setCustomerNum(num);
                            table.setWaitPerson(wp);
                            for(int i = 0; i < num; i++)
                                customerEs.execute(new Customer(table));
                        }
                    }
                    TimeUnit.MILLISECONDS.sleep(rand.nextInt(1000));
                }else {
                    break;
                }
            }
        }catch (InterruptedException e) {
            print("Restaurant interrupted");
        }
        print("Restaurant closed");
    }
}