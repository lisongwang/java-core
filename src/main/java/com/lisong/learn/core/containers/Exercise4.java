package com.lisong.learn.core.containers;

import com.lisong.learn.core.util.TextFile;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.lisong.learn.core.util.Print.print;

public class Exercise4 {

    private static class CollectionInitializer extends ArrayList<String> {

        private static final String REGEX = "(?m)(\\b\\w+\\b)";

        CollectionInitializer(String filePath) {
            init(filePath);
        }

        private void init(String filePath) {
            Matcher m = Pattern.compile(REGEX).matcher(TextFile.readFile(new File(filePath)));
            while(m.find()) {
                add(m.group());
            }
        }
    }

    public static void main(String[] args) {

        CollectionInitializer c = new CollectionInitializer(
                "JavaCore/src/main/java/com/lisong/learn/core/containers/Exercise4.java");
        print(c);
    }
}