package com.lisong.learn.core.strings.regex;

import com.lisong.learn.core.util.TextFile;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.lisong.learn.core.util.Print.print;

public class ClassName implements RegexCommand {
    private static String regex = "\\s*class\\s+([A-Z]\\w*)\\s+";
    private static String regex_c = "\\s*(//|/\\*|\\*)"; //comments

    @Override
    public void execute(String path) {

        Matcher m = Pattern.compile(regex).matcher("");
        Matcher mc = Pattern.compile(regex_c).matcher("");

        for(File f : TextFile.collectFiles(path)) {
            boolean printFile = false;
            for(String line : TextFile.readLines(f)) {
                m.reset(line);
                mc.reset(line);
                while(m.find() && !mc.find()) {
                    if(!printFile) {
                        print("File: " + f.getName() + "\n------------");
                        printFile = true;
                    }
                    print(m.group(1));
                }
            }
        }
    }
}
