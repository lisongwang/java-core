package com.lisong.learn.core.strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.lisong.learn.core.util.Print.print;

public class Exercise7 {

    private String regex = "[A-Z].*[\\.]";
    private Pattern p = Pattern.compile(regex);

    private void checkPattern (String sentence) {
        Matcher m = p.matcher(sentence);
        if(m.matches())
            print("[" + sentence + "]" + " is match pattern!");
        else
            print("[" + sentence + "]" + " is not match pattern!");
    }

    public static void main(String[] args) {

        Exercise7 exe7 = new Exercise7();
        String s1 = "Once upon a time.";
        String s2 = "abcd.";
        String s3 = "Abcd?";
        String s4 = "An easy way out.";
        String s5 = "Zorro.";
        String s6 = "X.";
        exe7.checkPattern(s1);
        exe7.checkPattern(s2);
        exe7.checkPattern(s3);
        exe7.checkPattern(s4);
        exe7.checkPattern(s5);
        exe7.checkPattern(s6);
    }
}
