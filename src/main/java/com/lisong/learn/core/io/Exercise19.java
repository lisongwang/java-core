package com.lisong.learn.core.io;

import com.lisong.learn.core.util.BinaryFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.lisong.learn.core.util.Print.print;

public class Exercise19 {

    public static void main(String[] args) {
        if(args.length < 1) {
            print("Provide the file name!");
            System.exit(0);
        }
        try {
            byte[] bytes = BinaryFile.read(args[0]);
            Map<Byte,Integer> map = new HashMap<>();
            for(byte b : bytes) {
                Integer i = map.get(b);
                map.put(b, i == null ? 1 : i+1);
            }
            print(map);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}