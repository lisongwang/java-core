package com.lisong.learn.core.arrays;

import com.lisong.learn.core.util.Generators;
import com.lisong.learn.core.util.RandomGenerator;

import java.util.Arrays;
import java.util.Collections;

import static com.lisong.learn.core.util.Print.print;

public class Exercise23 {

    public static void main(String[] args) {

        Integer[] intArray = Generators.fillArray(Integer.class, new RandomGenerator.Integer(100), 25);
        print("before sorting: " + Arrays.toString(intArray));
        Arrays.sort(intArray, Collections.reverseOrder());
        print("after sorting: " + Arrays.toString(intArray));
    }
}
