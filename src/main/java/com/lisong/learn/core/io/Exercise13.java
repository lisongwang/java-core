package com.lisong.learn.core.io;

import java.io.*;

import static com.lisong.learn.core.util.Print.print;

public class Exercise13 {

    public static void main(String[] args) {
        if(args.length < 2) {
            print("Provide the input file as well as output file!");
            System.exit(0);
        }
        try (LineNumberReader lr = new LineNumberReader(new FileReader(new File(args[0])));
             PrintWriter out = new PrintWriter(new File(args[1]))) {
            String s;
            while((s = lr.readLine()) != null)
                out.println(lr.getLineNumber() + ": " + s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}