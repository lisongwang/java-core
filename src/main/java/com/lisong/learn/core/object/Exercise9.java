package com.lisong.learn.core.object;

/**
 * Show the autoboxing between primitive types and wrappers
 */
public class Exercise9 {

    static void sayByte(Byte b) { System.out.println("Byte, " + b);}

    static void sayInt(Integer i) {
        System.out.println("Int, " + i);
    }

    static void sayFloat(Float f) {
        System.out.println("Float, " + f);
    }

    static void sayCharacter(Character c) {
        System.out.println("Char, " + c);
    }

    static void sayDouble(Double d) {
        System.out.println("Double, " + d);
    }

    static void sayBoolean(Boolean b) { System.out.println("Boolean, " + b);}

    public static void main(String[] args) {

        byte by = 120;
        char c = '5';
        short s = 11500;
        int i = 5;
        long l = 123332L;
        float f = 0.5f;
        double d = 2.45d;
        boolean bl = true;

        Short S = s;
        System.out.println("Short, " + S);
        Long L = l;
        System.out.println("Long, " + l);

        sayByte(by);
        sayInt(i);
        sayFloat(f);
        sayCharacter(c);
        sayDouble(d);
        sayBoolean(bl);
    }
}
