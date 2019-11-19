package com.lisong.learn.core.initialization;

/**
 * Combine exercise 19, exercise 20.
 */
public class Exercise20 {

    static void printArray (String... strArray) {
        System.out.println("String array");
        for (String s : strArray)
            System.out.print(s + " ");
        System.out.println();
    }

    static void printArray (int i, String... strArray) {

        System.out.println("String array with int: " + i);
        for (String s : strArray)
            System.out.print(s + " ");
        System.out.println();
    }

    static void printArray (float i, Object... objArray) {
        System.out.println("Object array");
        for (Object o : objArray)
            System.out.print(o + " ");
        System.out.println();
    }

    static void f(float f, Character... cArray) {
        System.out.println("Char array with f " + f);
        for (char c : cArray)
            System.out.print(c + " ");
        System.out.println();
    }

    static void f(int i, Character... cArray) {
        System.out.println("Char array with i " + i);
        for (char c : cArray)
            System.out.print(c + " ");
        System.out.println();
    }

    public static void main(String... args) {

        for (String s : args)
            System.out.print(s + " ");
        System.out.println();

        printArray("first", "second", "third");
        printArray(new String[] {"first", "second", "third"});
        printArray(10, (Object[])new Integer[] {1, 2, 3});
        printArray(1, "abc");

        printArray("abc");
        f(1, 'a');
        f('a', 'b');
    }
}
