package com.lisong.learn.core.strings.regex;

import com.lisong.learn.core.util.TextFile;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LiteralString implements RegexCommand {
    private static String regex = "(\"[^\"]*\")";

    @Override
    public void execute(String path) {
        Matcher m = Pattern.compile(regex).matcher("");
        for(File f : TextFile.collectFiles(path)) {
            FileMatcher.matchFile(f, m);
        }
    }
}
