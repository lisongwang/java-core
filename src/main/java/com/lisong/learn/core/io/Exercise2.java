package com.lisong.learn.core.io;

import com.lisong.learn.core.io.util.SortedDirList;

import java.io.File;

import static com.lisong.learn.core.util.Print.print;

public class Exercise2 {

    public static void main(String[] args) {
        SortedDirList sl = new SortedDirList(new File("JavaCore/src/main/java/com/lisong/learn/core/containers/util"));
        for(String item : sl.list("SimpleHashMap.*\\.java"))
            print(item);
        for(File file : sl.listFiles("SimpleHashMap.*\\.java"))
            print(file.getPath());
    }
}