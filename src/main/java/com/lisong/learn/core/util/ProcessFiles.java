package com.lisong.learn.core.util;

import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

public class ProcessFiles {

    private String regex;
    private Consumer<File> process;

    public ProcessFiles(String regex, Consumer<File> process) {
        this.regex = regex;
        this.process = process;
    }

    public void start(String[] args) {
        try {
            if(args.length == 0) {
                processDir(new File("."));
            }else {
                for(String arg : args) {
                    File file = new File(arg);
                    if(file.isDirectory())
                        processDir(file);
                    else {
                        process.accept(file.getCanonicalFile());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processDir(File dir) throws IOException {
        for(File file : Directory.walk(dir.getAbsolutePath(), regex))
            process.accept(file.getCanonicalFile());
    }
}