package com.lisong.learn.core.io;

import com.lisong.learn.core.util.TextFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static com.lisong.learn.core.util.Print.print;

public class Exercise17 {

    public static void main(String[] args) {
        if(args.length < 1) {
            print("Provide the file!");
            System.exit(0);
        }
        String text = TextFile.readFile(new File(args[0]));
        Map<Character,Integer> map = new HashMap<>();
        for(char c : text.toCharArray()) {
            Integer i = map.get(c);
            if(c >= 'A' && c <= 'z')
                map.put(c, i == null ? 1 : i + 1);
        }
        print(map);
    }
}