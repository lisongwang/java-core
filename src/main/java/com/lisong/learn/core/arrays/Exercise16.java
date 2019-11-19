package com.lisong.learn.core.arrays;

import com.lisong.learn.core.util.ConvertPrimitive;
import com.lisong.learn.core.util.Generators;
import com.lisong.learn.core.util.SkipGenerator;

import java.util.Arrays;

import static com.lisong.learn.core.util.Print.print;

public class Exercise16 {

    private static final int SIZE = 10;

    public static void main(String[] args) {

        boolean[] a1 = ConvertPrimitive.primitive(
                new boolean[0], Generators.fillArray(Boolean.class, new SkipGenerator.Boolean(1), SIZE));
        byte[] a2 = ConvertPrimitive.primitive(
                new byte[0], Generators.fillArray(Byte.class, new SkipGenerator.Byte(2), SIZE));
        short[] a3 = ConvertPrimitive.primitive(
                new short[0], Generators.fillArray(Short.class, new SkipGenerator.Short(3), SIZE));
        char[] a4 = ConvertPrimitive.primitive(
                new char[0], Generators.fillArray(Character.class, new SkipGenerator.Character(4), SIZE));
        int[] a5 = ConvertPrimitive.primitive(
                new int[0], Generators.fillArray(Integer.class, new SkipGenerator.Integer(5), SIZE));
        long[] a6 = ConvertPrimitive.primitive(
                new long[0], Generators.fillArray(Long.class, new SkipGenerator.Long(6), SIZE));
        float[] a7 = ConvertPrimitive.primitive(
                new float[0], Generators.fillArray(Float.class, new SkipGenerator.Float(7), SIZE));
        double[] a8 = ConvertPrimitive.primitive(
                new double[0], Generators.fillArray(Double.class, new SkipGenerator.Double(8), SIZE));
        String[] a9 = Generators.fillArray(String.class, new SkipGenerator.String(9), SIZE);

        print("a1 = " + Arrays.toString(a1));
        print("a2 = " + Arrays.toString(a2));
        print("a3 = " + Arrays.toString(a3));
        print("a4 = " + Arrays.toString(a4));
        print("a5 = " + Arrays.toString(a5));
        print("a6 = " + Arrays.toString(a6));
        print("a7 = " + Arrays.toString(a7));
        print("a8 = " + Arrays.toString(a8));
        print("a9 = " + Arrays.toString(a9));
    }
}