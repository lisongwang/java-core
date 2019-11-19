package com.lisong.learn.core.strings;

import com.lisong.learn.core.strings.regex.FileMatcher;
import com.lisong.learn.core.util.TextFile;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.lisong.learn.core.util.Print.print;

/**
 * Combine exercise 15, exercise 16.
 */
public class Exercise16 {

    public static void main(String[] args) {

        if(args.length < 3) {
            print("Usage: java Exercise16 file regex flag"); // flag: i|m|s
            System.exit(0);
        }

        int flag = 0x0000;
        for(String s : args[2].split("\\|")) {
            if(s.equals("i")) {
                flag |= Pattern.CASE_INSENSITIVE;
            }else if(s.equals("m")) {
                flag |= Pattern.MULTILINE;
            }else if(s.equals("s")) {
                flag |= Pattern.DOTALL;
            }else {
                print("Invalid flag!");
                System.exit(0);
            }
        }

        Matcher m = Pattern.compile(args[1], flag).matcher("");
        for(File f : TextFile.collectFiles(args[0])) {
            FileMatcher.matchFileByLine(f, m);
        }
    }
}
