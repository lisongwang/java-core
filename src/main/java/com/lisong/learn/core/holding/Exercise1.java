package com.lisong.learn.core.holding;

import java.util.ArrayList;
import java.util.Iterator;

import static com.lisong.learn.core.util.Print.print;

public class Exercise1 {

    public static void main(String[] args) {

        ArrayList<Gerbil> ga = new ArrayList<Gerbil>();
        ga.add(new Gerbil(1));
        ga.add(new Gerbil(2));
        ga.add(new Gerbil(3));
        Iterator<Gerbil> it = ga.iterator();
        while(it.hasNext()) {
            it.next().hop();
        }
    }
}

class Gerbil {

    private int gerbilNumber;

    Gerbil(int n) {
        gerbilNumber = n;
    }

    void hop() {

        print("GerbilNumber: " + gerbilNumber + " is hopping");
    }

    @Override
    public String toString() {
        return "GerbilNumber: " + gerbilNumber;
    }

    @Override
    public boolean equals(Object obj) {
        return gerbilNumber == ((Gerbil)obj).gerbilNumber;
    }
}