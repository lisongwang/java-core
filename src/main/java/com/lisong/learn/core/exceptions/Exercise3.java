package com.lisong.learn.core.exceptions;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

public class Exercise3 {

    private static final Logger log = Logger.getLogger("Exercise3");

    private static void f() {

        try {
            int[] ints = new int[8];
            for (int i = 0; i < 10; i++)
                ints[i] = i;
        }catch (ArrayIndexOutOfBoundsException e){
            StringWriter sr = new StringWriter();
            e.printStackTrace(new PrintWriter(sr));
            log.severe(sr.toString());
        }
    }

    public static void main(String[] args) {

        f();
    }
}
