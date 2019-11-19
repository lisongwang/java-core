package com.lisong.learn.core.util;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collection;

public class Print {

    public static void print() {
        System.out.println();
    }

    public static void print(Object obj) {
        System.out.println(obj);
    }

    public static void printnb(Object obj) {
        System.out.print(obj);
    }

    public static PrintStream print(String format, Object... args) {
        return System.out.printf(format, args);
    }

    public static void printBinaryInt(String s, int i) {

        System.out.println(s + "  int: " + i + "  binary: " + Integer.toBinaryString(i));
    }

    public static void printBinaryLong(String s, long l) {

        System.out.println(s + "  long: " + l + "  binary: " + Long.toBinaryString(l));
    }

    public static void printBinaryChar(String s, char c) {

        System.out.println(s + "  char: " + c + "  binary: " + Integer.toBinaryString(c));
    }

    public static void pprint(Collection<?> c) {
        System.out.println(pFormat(c));
    }

    public static void pprint(Object[] o) {
        pprint(Arrays.asList(o));
    }

    public static String pFormat (Collection<?> c) {
        StringBuilder sb = new StringBuilder("[");
        if(c == null || c.size() == 0)
            return sb.append("]").toString();
        for(Object element : c) {
            if(c.size() != 1)
                sb.append("\n");
            sb.append(element);
        }
        if(c.size() != 1)
            sb.append("\n");
        return sb.append("]").toString();
    }
}