package com.lisong.learn.core.strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.lisong.learn.core.util.Print.print;

public class Exercise10 {

    public static void main(String[] args) {

        String[] regxs = new String[] {
                "^Java",
                "\\Breg.*?",
                "n.w\\s+h(a|i)s",
                "s?",
                "s*",
                "s+",
                "s{4}",
                "s{1}",
                "s{0,3}"
        };

        String input = "Java now has regular expressions";
        for(String regx : regxs) {
            print("Input regx: " + regx + " ------------------");
            Pattern p = Pattern.compile(regx);
            Matcher m = p.matcher(input);
            while(m.find()) {
                print("Match \"" + m.group() + "\" at position " +
                        m.start() + "-" + (m.end() - 1));
            }
        }
    }
}
