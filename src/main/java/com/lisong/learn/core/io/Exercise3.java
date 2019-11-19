package com.lisong.learn.core.io;

import com.lisong.learn.core.io.util.SortedDirList;

import java.io.File;

import static com.lisong.learn.core.util.Print.print;

public class Exercise3 {

    public static void main(String[] args) {
        SortedDirList sl = new SortedDirList(new File("JavaCore/src/main/java/com/lisong/learn/core/containers/util"));
        File[] files;
        if(args.length == 0)
            files = sl.listFiles();
        else
            files = sl.listFiles(args[0]);
        long totalSize = 0;
        print("%1$-30s%2$-6s\n", "Name", "Size(byte)");
        for(File f : files) {
            if(f.isFile()) {
                print("%1$-30s%2$-6d\n", f.getName(), f.length());
                totalSize += f.length();
            }
        }
        print();
        print("Total size of above files: " + totalSize);
    }
}