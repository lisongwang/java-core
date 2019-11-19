package com.lisong.learn.core.util;

import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

public class ShowMethods {

    private static Pattern p1 = Pattern.compile("\\w+\\.");
    private static Pattern p2 = Pattern.compile("\\s+(native|final)\\s+");

    public static void extractMethodsWithoutFinalAndNative(Class<?> clazz, PrintStream out) {

        out.println(clazz.getName());
        for(Method m : clazz.getMethods()) {
            if(!p2.matcher(m.toString()).find())
                out.println("\t" + p1.matcher(m.toString()).replaceAll(""));
        }
        for(Constructor c : clazz.getConstructors()) {
            if(!p2.matcher(c.toString()).find())
                out.println("\t" + p1.matcher(c.toString()).replaceAll(""));
        }
    }

    public static void extractMethodsWithString(Class<?> clazz, PrintStream out, String s) {

        out.println(clazz.getName());
        for(Method m : clazz.getMethods()) {
            if(s == null || s.length() == 0 || m.getName().contains(s))
                out.println("\t" + p1.matcher(m.toString()).replaceAll(""));
        }
        for(Constructor c : clazz.getConstructors()) {
            if(s == null || s.length() == 0 || c.getName().contains(s))
                out.println("\t" + p1.matcher(c.toString()).replaceAll(""));
        }
    }
}