package com.lisong.learn.core.reusing;

/**
 * Show the lazy initialization for composite object.
 */
public class Exercise1 {

    private Composite comp;

    public static void main(String[] args) {

        Exercise1 exe1 = new Exercise1();
        System.out.println(exe1);
    }

    public String toString() {

        if (this.comp == null)
            this.comp = new Composite();

        return "In Exercise3 that " + comp;
    }
}

class Composite {

    public String toString() {
        return "Complete function in Composite!";
    }

    public static void main(String[] args) {
        System.out.println("Also works!");
    }
}