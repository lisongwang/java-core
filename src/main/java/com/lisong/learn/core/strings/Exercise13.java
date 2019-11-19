package com.lisong.learn.core.strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.lisong.learn.core.util.Print.print;

public class Exercise13 {

    private static class Display {

        boolean regexPrinted = false;
        private String regex;
        Display(String regex) { this.regex = regex; }
        void display(String message) {
            if(!regexPrinted) {
                print("regex: " + regex);
                regexPrinted = true;
            }
            print(message);
        }
    }

    static void examine(String regex, String input) {

        Display d = new Display(regex);

        Matcher m = Pattern.compile(regex).matcher(input);
        while(m.find()) {
            d.display("Find " + m.group() + " at " + m.start() + " - " + (m.end()));
        }
        if(m.lookingAt()) {
            d.display("Looking at " + m.start() + " - " + (m.end()));
        }
        if(m.matches()) {
            d.display("Matchers at " + m.start() + " - " + (m.end()));
        }
    }

    public static void main(String[] args) {

        String[] inputs = Exercise12.POEM.split("\n");
        for(String input : inputs) {
            print("input: " + input);
            for(String regex : new String[] {
                    "\\w*ere\\w*", "\\w*ever", "T\\w+", "Never.*?!"
            }) {
                examine(regex, input);
            }
        }
    }
}
