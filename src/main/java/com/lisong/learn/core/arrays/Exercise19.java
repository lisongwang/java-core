package com.lisong.learn.core.arrays;

import java.util.Arrays;

import static com.lisong.learn.core.util.Print.print;

public class Exercise19 {

    public static void main(String[] args) {

        IntObject[] a1 = {new IntObject(1), new IntObject(2), new IntObject(3)};
        IntObject[] a2 =  {new IntObject(1), new IntObject(2), new IntObject(3)};
        print("a1: " + Arrays.toString(a1));
        print("a2: " + Arrays.toString(a2));
        print("a1 equals a2? " + Arrays.equals(a1, a2));
    }
}

class IntObject implements Comparable<IntObject> {

    private int i;

    IntObject(int i) {
        this.i = i;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(!(obj instanceof IntObject))
            return false;
        return this.i == ((IntObject)obj).i;
    }

    @Override
    public String toString() {
        return Integer.toString(i);
    }

    @Override
    public int compareTo(IntObject o) {
        return Integer.compare(i, o.i);
    }
}