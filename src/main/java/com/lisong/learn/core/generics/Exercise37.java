package com.lisong.learn.core.generics;

import java.util.Date;

import static com.lisong.learn.core.util.Print.print;

public class Exercise37 {

    public static void main(String[] args) {

        Mixin mix1 = new Mixin(), mix2 = new Mixin();
        mix1.set("test for mix1");
        mix1.setColor("Red");
        mix2.set("test for mix2");
        mix2.setColor("Green");
        print(mix1.get() + " " + mix1.getStamp() + " " + mix1.getSerialNumber() + " " + mix1.getColor());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        print(mix2.get() + " " + mix2.getStamp() + " " + mix2.getSerialNumber() + " " + mix2.getColor());
    }
}

interface TimeStamp {
    long getStamp();
}

interface SerialNumbered {
    long getSerialNumber();
}

interface Basic {
    void set(String s);
    String get();
}

interface Colored {
    void setColor(String c);
    String getColor();
}

class BasicImpl implements Basic {
    private String s;
    @Override
    public void set(String s) {
        this.s = s;
    }

    @Override
    public String get() {
        return s;
    }
}

class SerialNumberedImpl implements SerialNumbered {
    private static long count = 1;
    @Override
    public long getSerialNumber() {
        return count++;
    }
}

class Mixin extends BasicImpl implements TimeStamp, SerialNumbered, Colored {
    private TimeStamp timeStamp = ()->new Date().getTime();
    private SerialNumbered serialNumbered = new SerialNumberedImpl();
    private Colored colored = new Colored() {
        private String color;
        @Override
        public void setColor(String c) { color = c; }
        @Override
        public String getColor() { return color; }
    };
    @Override
    public long getStamp() {
        return timeStamp.getStamp();
    }

    @Override
    public long getSerialNumber() {
        return serialNumbered.getSerialNumber();
    }

    @Override
    public String getColor() {
        return colored.getColor();
    }

    @Override
    public void setColor(String c) {
        colored.setColor(c);
    }
}

