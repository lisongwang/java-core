package com.lisong.learn.core.io.util;

import java.io.File;
import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Pattern;

public class SortedDirList {

    private File file;
    private String[] emptyItem = new String[0];
    private File[] emptyFile = new File[0];

    public SortedDirList(File file) {
        this.file = file;
    }

    public String[] list() {
        if(file == null)
            return emptyItem;
        String[] dirItems = Optional.ofNullable(file.list()).orElse(emptyItem);
        Arrays.sort(dirItems,String.CASE_INSENSITIVE_ORDER);
        return dirItems;
    }

    public String[] list(String regex) {
        if(file == null)
            return emptyItem;
        Pattern p = Pattern.compile(regex);
        String[] dirItems = Optional.ofNullable(file.list((f,n)->p.matcher(n).matches())).orElse(emptyItem);
        Arrays.sort(dirItems,String.CASE_INSENSITIVE_ORDER);
        return dirItems;
    }

    public File[] listFiles() {
        if(file == null)
            return emptyFile;
        File[] files = Optional.ofNullable(file.listFiles()).orElse(emptyFile);
        Arrays.sort(files,(f1,f2)->String.CASE_INSENSITIVE_ORDER.compare(f1.getName(),f2.getName()));
        return files;
    }

    public File[] listFiles(String regex) {
        if(file == null)
            return emptyFile;
        Pattern p = Pattern.compile(regex);
        File[] files = Optional.ofNullable(file.listFiles((f,n)->p.matcher(n).matches())).orElse(emptyFile);
        Arrays.sort(files,(f1,f2)->String.CASE_INSENSITIVE_ORDER.compare(f1.getName(),f2.getName()));
        return files;
    }
}