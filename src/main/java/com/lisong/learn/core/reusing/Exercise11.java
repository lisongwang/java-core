package com.lisong.learn.core.reusing;

/**
 * Show how to use delegation instead of inheritance
 */
public class Exercise11 {

    public static void main(String[] args) {

        Detergent2 x = new Detergent2();
        x.dilute();
        x.apply();
        x.scrub();
        x.foam();
        System.out.println(x);
    }
}

class Detergent2 {

    private final MyCleanser cleanser = new MyCleanser();

    public void foam() { append(" foam()"); }

    public void append(String a) {
        cleanser.append(a);
    }

    public void dilute() {
        cleanser.dilute();
    }

    public void apply() {
        cleanser.apply();
    }

    public void scrub() {
        cleanser.scrub();
    }

    public String toString() {
        return cleanser.toString();
    }

    private class MyCleanser extends Cleanser {
        @Override
        public void scrub() {
            append(" Detergent.scrub()");
            super.scrub();
        }
    }
}