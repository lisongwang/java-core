package com.lisong.learn.core.strings;

import java.util.Scanner;

import static com.lisong.learn.core.util.Print.print;

public class Exercise20 {

    private int i;
    private long l;
    private String s;
    private float f;
    private double d;

    Exercise20(String initText) {

        Scanner scanner = new Scanner(initText).useDelimiter("\\s*,\\s*");
        i = scanner.nextInt();
        l = scanner.nextLong();
        s = scanner.next();
        f = scanner.nextFloat();
        d = scanner.nextDouble();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("i: " + i + "\n");
        sb.append("l: " + l + "\n");
        sb.append("s: " + s + "\n");
        sb.append("f: " + f + "\n");
        sb.append("d: " + d + "\n");
        return sb.toString();
    }

    public static void main(String[] args) {

        String initText = "20, 200, exercise20, 20.22, 20.2222";
        Exercise20 exe20 = new Exercise20(initText);
        print(exe20);
    }
}
