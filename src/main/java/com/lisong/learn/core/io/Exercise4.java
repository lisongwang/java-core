package com.lisong.learn.core.io;

import com.lisong.learn.core.util.Directory;

import java.io.File;
import java.util.List;

import static com.lisong.learn.core.util.Print.print;

public class Exercise4 {

    public static void main(String[] args) {
        List<File> files;
        if(args.length == 0)
            files = Directory.walk(".").files;
        else
            files = Directory.walk("/Users/gavin/Documents/copy_from_old" +
                    "/Documents/OracleCloud/IOTCS/iotappscs/VoF/spark/src/main/java/oracle/apps/vof" +
                    "/spark/mdap", args[0]).files;
        long totalSize = 0;
        print("%1$-60s%2$-12s\n", "Name", "Size(byte)");
        for(File f : files) {
            print("%1$-60s%2$-12d\n", f.getName(), f.length());
            totalSize += f.length();
        }
        print();
        print("Total files: " + files.size() + " Total size: " + totalSize);
    }
}