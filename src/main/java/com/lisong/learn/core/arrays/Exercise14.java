package com.lisong.learn.core.arrays;

import com.lisong.learn.core.util.ConvertPrimitive;
import com.lisong.learn.core.util.CountingGenerator;
import com.lisong.learn.core.util.Generators;

import java.util.Arrays;

import static com.lisong.learn.core.util.Print.print;

public class Exercise14 {

    private static final int SIZE = 10;

    public static void main(String[] args) {

        boolean[] boArray = new boolean[SIZE];
        byte[] byArray = new byte[SIZE];
        short[] shArray = new short[SIZE];
        char[] chArray = new char[SIZE];
        int[] inArray = new int[SIZE];
        long[] loArray = new long[SIZE];
        float[] flArray = new float[SIZE];
        double[] doArray = new double[SIZE];

        ConvertPrimitive.primitive(boArray, Generators.fillArray(Boolean.class, new CountingGenerator.Boolean(), SIZE));
        ConvertPrimitive.primitive(byArray, Generators.fillArray(Byte.class, new CountingGenerator.Byte(), SIZE));
        ConvertPrimitive.primitive(shArray, Generators.fillArray(Short.class, new CountingGenerator.Short(), SIZE));
        ConvertPrimitive.primitive(chArray, Generators.fillArray(Character.class, new CountingGenerator.Character(), SIZE));
        ConvertPrimitive.primitive(inArray, Generators.fillArray(Integer.class, new CountingGenerator.Integer(), SIZE));
        ConvertPrimitive.primitive(loArray, Generators.fillArray(Long.class, new CountingGenerator.Long(), SIZE));
        ConvertPrimitive.primitive(flArray, Generators.fillArray(Float.class, new CountingGenerator.Float(), SIZE));
        ConvertPrimitive.primitive(doArray, Generators.fillArray(Double.class, new CountingGenerator.Double(), SIZE));

        print(Arrays.toString(boArray));
        print(Arrays.toString(byArray));
        print(Arrays.toString(shArray));
        print(Arrays.toString(chArray));
        print(Arrays.toString(inArray));
        print(Arrays.toString(loArray));
        print(Arrays.toString(flArray));
        print(Arrays.toString(doArray));
    }
}
