package com.lisong.learn.core.reusing;

/**
 * Show how to use blank final.
 */
public class Exercise19 {

    final Poppet p;

    Exercise19() { p = new Poppet(1); }
    Exercise19(int i) { p = new Poppet(i); }

    public static void main(String[] args) {

        Exercise19 exe19_1 = new Exercise19();
        System.out.println(exe19_1.p.i);
        Exercise19 exe19_2 = new Exercise19(10);
        //exe19_1.p = new Poppet(2); // can't rebind the final reference to another object
        exe19_1.p.i++;
        System.out.println(exe19_1.p.i);
        System.out.println(exe19_2.p.i);
    }
}

class Poppet {

    int i;
    Poppet(int i) { this.i = i; }
}
