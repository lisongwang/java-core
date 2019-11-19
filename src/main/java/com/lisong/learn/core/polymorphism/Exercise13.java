package com.lisong.learn.core.polymorphism;

import static com.lisong.learn.core.util.Print.print;

public class Exercise13 {

    public static void main(String[] args) {

        Shared shared = new Shared();
        Composing[] comArray = new Composing[5];
        for(int i = 0; i < comArray.length; i++) {
            comArray[i] = new Composing(shared);
        }

        for(Composing c : comArray)
            c.dispose();

        for(int j = 0; j < comArray.length; j++)
            comArray[j] = null;

        shared.addRef();
        //shared.dispose();
        shared = null;
        comArray = null;
        System.gc();
    }
}

class Shared {

    private int refcount;
    private static long count = 0;
    private final long id = count++;

    void addRef() { refcount++; }

    Shared() { print("Creating " + this); }
    void dispose() {
        if(--refcount == 0)
            print("Disposing " + this);
    }

    @Override
    public String toString() {
        return "Shared " + id;
    }

    @Override
    protected void finalize() throws Throwable {
        if(refcount > 0)
            print("Shared " + id + " still in use");
        else
            print("Shared " + id + " can be disposed");
    }
}

class Composing {

    private Shared shared;
    private static long count = 0;
    private final long id = count++;
    public Composing(Shared shared) {
        print("Creating " + this);
        this.shared = shared;
        shared.addRef();
    }
    public void dispose() {
        print("Disposing " + this);
        shared.dispose();
    }

    @Override
    public String toString() {
        return "Composing " + id;
    }
}