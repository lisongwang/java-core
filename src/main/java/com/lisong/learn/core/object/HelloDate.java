package com.lisong.learn.core.object;

import java.util.Date;

/**
 * The first Thinking in Java example program
 * Display a string and today's date.
 * @author lisong
 * @author www.lisong.com
 * @version 4.0
 */
public class HelloDate {

    public class Documentation1 {

        /** A field comment */
        public int i;

        /** A method comment */
        public void f() {}
    }

    /**
     * <pre>
     *     System.out.println(new Date());
     * </pre>
     */
    public class Documentation2 {

        /** Just a comment*/
    }

    /**
     * You can <em>even</em> insert a list:
     * <ol>
     *     <li>Item one</li>
     *     <li>Item two</li>
     *     <li>Item three</li>
     * </ol>
     */
    public class Documentation3 {


    }

    /**
     * Entry point to class application.
     * @param args args array of string arguments
     */
    public static void main(String[] args) {

        System.out.println("Hello, it's:");
        System.out.println(new Date());
    }
}
