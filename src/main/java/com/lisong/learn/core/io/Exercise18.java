
package com.lisong.learn.core.io;

import com.lisong.learn.core.io.util.TextFile;

import java.io.File;
import java.io.IOException;
import java.util.TreeSet;

import static com.lisong.learn.core.util.Print.print;

public class Exercise18 {

    public static void main(String[] args) {
        if(args.length < 3) {
            print("Provide the file names!");
            System.exit(0);
        }
        try {
            String text = TextFile.read(new File(args[0]));
            TextFile.write(new File(args[1]), text);
            TextFile tf = new TextFile(args[1]);
            tf.write(args[2]);
            TreeSet<String> words = new TreeSet<>(new TextFile(args[2], "\\W+"));
            print(words.headSet("e"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}