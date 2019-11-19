package com.lisong.learn.core.strings;

import java.util.Arrays;
import java.util.regex.Pattern;

import static com.lisong.learn.core.util.Print.print;

public class Exercise14 {

    public static void main(String[] args) {

        String input = "This!!unusual use!!of exclamation!!points";
        print(Arrays.toString(input.split("!!")));
        print(Arrays.toString(Pattern.compile("!!").split(input, 3)));
    }
}
