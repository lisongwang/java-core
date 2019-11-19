package com.lisong.learn.core.strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.lisong.learn.core.util.Print.print;

public class Exercise11 {

    public static void main(String[] args) {

        String input = "Arlin ate eight apples and one orange while Anita hadn't any";
        Pattern p = Pattern.compile("(?i)(([^aeiou])|(\\s+[aeiou]))\\w+?[aeiou]\\b");
        Matcher m = p.matcher(input);
        while(m.find()) {
            print("Match \"" + m.group() + "\" at position " +
                    m.start() + "-" + (m.end() - 1));
        }
    }
}
