package com.lisong.learn.core.arrays;

import com.lisong.learn.core.util.ConvertPrimitive;
import com.lisong.learn.core.util.CountingGenerator;
import com.lisong.learn.core.util.Generators;

import java.util.Arrays;

import static com.lisong.learn.core.util.Print.print;

/**
 * Combine exercise 11, exercise 12.
 */
public class Exercise12 {

    public static void main(String[] args) {

        //autoboxing doesn't work with arrays
        int[] iArray1 = new int[10];
        iArray1[0] = 10;
        //iArray1[0].getClass() doesn't work
        print("iArray1[0]: " + iArray1[0]);
        Integer[] iArray2 = new Integer[10];
        iArray2[0] = 20;
        print("iArray2[0] type: " + iArray2[0].getClass());
        //iArray1 = (int[])iArray2;
        //iArray2 = (Integer[])iArray1;
        int[][][] iiiArray = new int[10][][];
        print("int[] type: " + int[].class);
        print("int[][][] type: " + int[][][].class);
        print("Integer[] type: " + Integer[].class);

        double[] doArray = ConvertPrimitive.primitive(new double[0],
                Generators.fillArray(Double.class, new CountingGenerator.Double(), 15));
        print(Arrays.toString(doArray));
    }
}
