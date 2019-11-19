package com.lisong.learn.core.io;

import com.lisong.learn.core.util.TextFile;

import java.io.File;
import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Pattern;

import static com.lisong.learn.core.util.Print.print;

public class Exercise1 {

    public static void main(String[] args) {
        String[] dirItems;
        String[] emptyItem = new String[0];
        File file = new File("JavaCore/src/main/java/com/lisong/learn/core/containers");
        if(args.length == 0) {
            dirItems = Optional.ofNullable(file.list((f,n)->new File(f,n).isFile())).orElse(emptyItem);
        }else {
            dirItems = Optional.ofNullable(file.list((f,n)->{
                File f1 = new File(f,n);
                if(f1.isFile()) {
                    String words = TextFile.readFile(f1);
                    for(String word : args) {
                        if(Pattern.compile(word).matcher(words).find())
                            return true;
                    }
                    return false;
                }
                return false; })).orElse(emptyItem);
        }
        Arrays.sort(dirItems,String.CASE_INSENSITIVE_ORDER);
        for(String dirItem : dirItems)
            print(dirItem);
    }
}