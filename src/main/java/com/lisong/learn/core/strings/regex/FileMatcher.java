package com.lisong.learn.core.strings.regex;

import com.lisong.learn.core.util.TextFile;

import java.io.File;
import java.util.regex.Matcher;

import static com.lisong.learn.core.util.Print.print;

/**
 * This class used to display match result for a single file.
 */
public class FileMatcher {

    // Read file by line do match
    public static void matchFileByLine(File file, Matcher m) {
        int index = 0;
        boolean printFile = false;
        for(String s : TextFile.readLines(file)) {
            m.reset(s);
            index++;
            while(m.find()) {
                if(!printFile) {
                    print("File: " + file.getName() + "\n------------");
                    printFile = true;
                }
                print("line: " + index + ": " + m.group() + ": " + m.start() +
                        " - " + (m.end() -1));
            }
        }
    }

    // Read file to a string and do match
    public static void matchFile(File file, Matcher m) {

        m.reset(TextFile.readFile(file));
        boolean printFile = false;
        while(m.find()) {
            if(!printFile) {
                print("File: " + file.getName() + "\n------------");
                printFile = true;
            }
            print(m.group());
        }
    }
}
