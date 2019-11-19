package com.lisong.learn.core.strings;

import java.util.Arrays;

import static com.lisong.learn.core.util.Print.print;

/**
 * Combine exercise 8, exercise 9.
 */
public class Exercise9 {

    public static void main(String[] args) {

        Splitting.split("the|you");
        Splitting.replace("[aeiouAEIOU]", "_");
    }
}

class Splitting {

    static String knights = "Then, when you have found the shrubbery, you must " +
            "cut down mightiest tree in the forest... with a herring!";

    static void split(String regx) {
        print(Arrays.toString(knights.split(regx)));
    }

    static void replace(String regx, String rp) {
        print(knights.replaceAll(regx, rp));
    }
}
