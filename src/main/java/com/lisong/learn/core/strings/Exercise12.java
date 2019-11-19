package com.lisong.learn.core.strings;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.lisong.learn.core.util.Print.print;
import static com.lisong.learn.core.util.Print.printnb;

public class Exercise12 {

    static public final String POEM =
            "Twas brillig, and the slithy toves\n" +
                    "Did gyre and gimble in the wabe.\n" +
                    "All mimsy were the borogoves,\n" +
                    "And the mome raths outgrabe.\n" +
                    "Beware the Jabberwock, my son,\n" +
                    "The jaws that bite, the claws that catch,\n" +
                    "Beware the Jubjub bird, and shun\n" +
                    "The frumious Bandersnatch.";

    public static void main(String[] args) {

        Matcher m = Pattern.compile("(?m)(\\b[a-z]\\w*\\b)").matcher(POEM);
        Set<String> wordSet = new LinkedHashSet<>();
        while(m.find()) {
            wordSet.add(m.group());
        }
        print("Count of unique words that do not start with capital letter is " + wordSet.size());
        for(String s : wordSet) {
            printnb(s + " ");
        }
    }
}
