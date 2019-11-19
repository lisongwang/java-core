package com.lisong.learn.core.io;

import com.lisong.learn.core.util.ProcessFiles;

public class Exercise5 {

    public static void main(String[] args) {
        new ProcessFiles("(?i).*\\$.*\\.class", System.out::println).start(args);
    }
}