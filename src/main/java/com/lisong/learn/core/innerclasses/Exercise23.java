package com.lisong.learn.core.innerclasses;

import static com.lisong.learn.core.util.Print.print;

public class Exercise23 {

    Exercise23 (int size) { items = new U[size]; }

    private U[] items;

    void store(U u, int i) {
        items[i] = u;
    }

    void remove(int i) {
        items[i] = null;
    }

    void show() {
        for(U u : items) {
            //u.start();
            if(u != null)
                u.doIt();
            //u.stop();
        }
    }
    public static void main(String[] args) {

        Exercise23 exe23 = new Exercise23(10);
        exe23.store(new Outer23().getU(), 0);
        exe23.store(new Outer23().getU(), 1);
        exe23.store(new Outer23().getU(), 2);
        exe23.store(new Outer23().getU(), 3);
        exe23.show();
        exe23.remove(1);
        exe23.remove(3);
        exe23.show();
    }
}

interface U {

    void start();
    void doIt();
    void stop();
}

class Outer23 {

    private static int count = 0;
    private final int i = count++;

    U getU() {
        return new U() {
            @Override
            public void start() {
                print("start");
            }

            @Override
            public void doIt() {
                print(Outer23.this.toString());
            }

            @Override
            public void stop() {
                print("stop");
            }
        };
    }

    @Override
    public String toString() {
        return "Outer23 " + i;
    }
}

