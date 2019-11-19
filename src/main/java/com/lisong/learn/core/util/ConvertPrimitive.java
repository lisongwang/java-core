package com.lisong.learn.core.util;

public class ConvertPrimitive {

    public static boolean[] primitive(boolean[] bArray1, Boolean[] bArray2) {
        if(bArray1.length < bArray2.length)
            bArray1 = new boolean[bArray2.length];
        for(int i = 0; i < bArray2.length; i++)
            bArray1[i] = bArray2[i];
        return bArray1;
    }

    public static byte[] primitive(byte[] bArray1, Byte[] bArray2) {
        if(bArray1.length < bArray2.length)
            bArray1 = new byte[bArray2.length];
        for(int i = 0; i < bArray2.length; i++)
            bArray1[i] = bArray2[i];
        return bArray1;
    }

    public static short[] primitive(short[] bArray1, Short[] bArray2) {
        if(bArray1.length < bArray2.length)
            bArray1 = new short[bArray2.length];
        for(int i = 0; i < bArray2.length; i++)
            bArray1[i] = bArray2[i];
        return bArray1;
    }

    public static char[] primitive(char[] bArray1, Character[] bArray2) {
        if(bArray1.length < bArray2.length)
            bArray1 = new char[bArray2.length];
        for(int i = 0; i < bArray2.length; i++)
            bArray1[i] = bArray2[i];
        return bArray1;
    }

    public static int[] primitive(int[] bArray1, Integer[] bArray2) {
        if(bArray1.length < bArray2.length)
            bArray1 = new int[bArray2.length];
        for(int i = 0; i < bArray2.length; i++)
            bArray1[i] = bArray2[i];
        return bArray1;
    }

    public static long[] primitive(long[] bArray1, Long[] bArray2) {
        if(bArray1.length < bArray2.length)
            bArray1 = new long[bArray2.length];
        for(int i = 0; i < bArray2.length; i++)
            bArray1[i] = bArray2[i];
        return bArray1;
    }

    public static float[] primitive(float[] bArray1, Float[] bArray2) {
        if(bArray1.length < bArray2.length)
            bArray1 = new float[bArray2.length];
        for(int i = 0; i < bArray2.length; i++)
            bArray1[i] = bArray2[i];
        return bArray1;
    }

    public static double[] primitive(double[] bArray1, Double[] bArray2) {
        if(bArray1.length < bArray2.length)
            bArray1 = new double[bArray2.length];
        for(int i = 0; i < bArray2.length; i++)
            bArray1[i] = bArray2[i];
        return bArray1;
    }
}
