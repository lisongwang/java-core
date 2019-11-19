package com.lisong.learn.core.type;

import java.lang.reflect.Field;
import java.util.HashMap;

import static com.lisong.learn.core.util.Print.print;
import static com.lisong.learn.core.util.Print.printnb;

/**
 * Combine exercise 8, exercise 9.
 */
public class Exercise9 {

    static void printClass(Class c, int level) {
        if(c == null)
            return;

        printTab(level);
        print(c.getSimpleName());

        int fieldLevel = level + 1;
        printFields(c, fieldLevel);
        print("-----------------------");

        int classLevel = level + 1;
        printClass(c.getSuperclass(), classLevel);

        for(Class i : c.getInterfaces()) {
            int interfaceLevel = level + 1;
            printClass(i, interfaceLevel);
        }
    }

    private static void printFields(Class c, int level) {
        if(c == null)
            return;
        for(Field f : c.getDeclaredFields()) {
            printTab(level);
            print(f.getName());
        }
    }

    private static void printTab(int level) {
        for(int i = 1; i < level; i++)
            printnb("\t");
    }

    static void printObject(Object o) {
        int level = 1;
        printClass(o.getClass(), level);
    }
    public static void main(String[] args) {

        HashMap map = new HashMap();
        printObject(map);
    }
}