package com.lisong.learn.core.control;

import static com.lisong.learn.core.util.Print.print;

/**
 * Show the use of switch.
 */
public class Exercise8 {

    public static void main(String[] args) {

        String source = "abkdkelfkwjeffa";

        char[] charArray = source.toCharArray();

        final char labelA = 'a';

        for (int c : charArray) {

            switch (c) {

                case labelA:
                case 'b':
                case 'c':
                    print("Find a, b, c"); break;
                case 'd':
                case 'e':
                case 'f':
                    print("Find d, e, f"); break;
                default:
                    print("Other characters!");
            }
        }
    }
}
