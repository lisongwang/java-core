package com.lisong.learn.core.arrays;

import java.util.Arrays;

import static com.lisong.learn.core.util.Print.print;

/**
 * Combine exercise 1, exercise 2.
 */
public class Exercise2 {

    private static void testArray(BerylliumSphere[] sphereArray) {
        print(Arrays.toString(sphereArray));
    }

    private static BerylliumSphere[] newArray(int size) {
        BerylliumSphere[] sArray = new BerylliumSphere[size];
        for(int i = 0; i < sArray.length; i++)
            sArray[i] = new BerylliumSphere();
        return sArray;
    }

    public static void main(String[] args) {

        testArray(new BerylliumSphere[]{new BerylliumSphere(), new BerylliumSphere(), new BerylliumSphere()});
        BerylliumSphere[] sphereArray = {new BerylliumSphere(), new BerylliumSphere(), new BerylliumSphere()};
        testArray(sphereArray);
        testArray(newArray(10));
    }
}

class BerylliumSphere implements Comparable<BerylliumSphere> {
    private static long count = 0;
    private final long id = count++;

    @Override
    public String toString() {
        return "Sphere " + id;
    }

    @Override
    public int compareTo(BerylliumSphere o) {
        return Long.compare(id, o.id);
    }
}
