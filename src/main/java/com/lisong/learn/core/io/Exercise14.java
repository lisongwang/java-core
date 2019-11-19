package com.lisong.learn.core.io;

import com.lisong.learn.core.util.TextFile;

import java.io.*;
import java.time.Duration;
import java.time.Instant;

import static com.lisong.learn.core.util.Print.print;

public class Exercise14 {

    private static void writeWithBuffer(File file, final String[] lines) {
        try (PrintWriter out = new PrintWriter(file)){
            for(String s : lines)
                out.println(s);
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void writeWithoutBuffer(File file, final String[] lines) {
        try (FileOutputStream fout = new FileOutputStream(file)) {
            for(String s : lines) {
                for(byte b : s.getBytes())
                    fout.write(b);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if(args.length < 2) {
            print("Provide the input file as well as output file!");
            System.exit(0);
        }
        String[] lines = TextFile.readLines(new File(args[0]));
        Instant ins1 = Instant.now();
        writeWithBuffer(new File(args[1]), lines);
        Instant ins2 = Instant.now();
        print("writeWithBuffer duration: " + Duration.between(ins1, ins2).toMillis());
        Instant ins3 = Instant.now();
        writeWithoutBuffer(new File(args[1]), lines);
        Instant ins4 = Instant.now();
        print("writeWithoutBuffer duration: " + Duration.between(ins3, ins4).toMillis());
    }
}