package com.lisong.learn.core.arrays;

import com.lisong.learn.core.util.Generator;
import com.lisong.learn.core.util.Generators;

import java.util.Arrays;
import java.util.Random;

import static com.lisong.learn.core.util.Print.print;

public class Exercise22 {

    static final Generator<IntObject> RandomIntObjectGen = new Generator<IntObject>() {
        private Random rand = new Random(99);
        @Override
        public IntObject next() {
            return new IntObject(rand.nextInt(20));
        }
    };

    public static void main(String[] args) {

        IntObject[] array1 = new IntObject[20];
        Generators.fillArray(array1, RandomIntObjectGen);
        print("before sort: " + Arrays.toString(array1));
        int index = Arrays.binarySearch(array1, array1[new Random().nextInt(array1.length)]);
        print("index = " + index);
        Arrays.sort(array1);
        print("after sort: " + Arrays.toString(array1));
        index = Arrays.binarySearch(array1, array1[new Random().nextInt(array1.length)]);
        print("index = " + index + " for " + array1[index]);
    }
}
