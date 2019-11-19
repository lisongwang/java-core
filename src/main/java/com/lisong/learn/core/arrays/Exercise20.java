package com.lisong.learn.core.arrays;

import java.util.Arrays;

import static com.lisong.learn.core.util.Print.print;

public class Exercise20 {

    public static void main(String[] args) {

        int[][][] int1 = new int[2][3][3];
        int[][][] int2 = new int[2][3][3];
        long[][][] long1 = new long[2][3][3];
        Integer[][][] int3 = new Integer[2][3][3];
        IntObject[][][] array1 = new IntObject[2][3][3];
        IntObject[][][] array2 = new IntObject[2][3][3];

        print("int1 deepToString: " + Arrays.deepToString(int1));
        print("int2 deepToString: " + Arrays.deepToString(int2));
        print("array1 deepToString: " + Arrays.deepToString(array1));
        print("array2 deepToString: " + Arrays.deepToString(array2));
        print("long1 deepToString: " + Arrays.deepToString(long1));
        print("int3 deepToString: " + Arrays.deepToString(int3));
        print("int1 deepEquals int2: " + Arrays.deepEquals(int1, int2));
        print("int1 deepEquals long1: " + Arrays.deepEquals(int1, long1));
        print("int1 deepEquals int3: " + Arrays.deepEquals(int1, int3));
        print("array1 deepEquals array2: " + Arrays.deepEquals(array1, array2));
        print("array1 deepEquals int1: " + Arrays.deepEquals(array1, int1));
        print("array1 deepEquals int3: " + Arrays.deepEquals(array1, int3));
    }
}

