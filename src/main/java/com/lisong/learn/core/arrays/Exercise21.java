package com.lisong.learn.core.arrays;

import com.lisong.learn.core.util.Generators;

import java.util.Arrays;
import java.util.Collections;

import static com.lisong.learn.core.util.Print.print;

public class Exercise21 {

    private static void sortWithBerylliumSphere() {
        BerylliumSphere[] array1 = Generators.fillArray(BerylliumSphere.class, BerylliumSphere::new , 15);
        print("before sort: " + Arrays.toString(array1));
        Arrays.sort(array1);
        print("after sort: " + Arrays.toString(array1));
        Arrays.sort(array1, Collections.reverseOrder());
        print("after sort in reverse: " + Arrays.toString(array1));
    }

    public static void main(String[] args) {
        sortWithBerylliumSphere();
    }
}
