package com.lisong.learn.core.arrays;

import java.util.Arrays;
import java.util.Random;

import static com.lisong.learn.core.util.Print.print;

public class Exercise24 {

    private static void searchWithIntObject() {

        Random rand = new Random(88);
        IntObject[] array1 = new IntObject[20];
        for(int i = 0; i < 20; i++)
            array1[i] = new IntObject(rand.nextInt(1000));
        print("before sort: " + Arrays.toString(array1));
        Arrays.sort(array1);
        print("after sort: " + Arrays.toString(array1));
        int key = new Random().nextInt(1000);
        int index = Arrays.binarySearch(array1, new IntObject(key));
        print("index: " + index + " key: " + key);
    }

    public static void main(String[] args) {
        searchWithIntObject();
    }
}
