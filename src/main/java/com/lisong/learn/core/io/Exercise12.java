package com.lisong.learn.core.io;

import com.lisong.learn.core.util.TextFile;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import static com.lisong.learn.core.util.Print.print;

public class Exercise12 {

    public static void main(String[] args) {
        if(args.length < 2) {
            print("Provide the input file as well as output file!");
            System.exit(0);
        }
        List<String> lines = new LinkedList<>(Arrays.asList(TextFile.readLines(new File(args[0]))));
        try (PrintWriter out = new PrintWriter(new File(args[1]))){
            ListIterator<String> it = lines.listIterator();
            int count = 1;
            while(it.hasNext()) {
                String line = it.next();
                out.println(count++ + ": " + line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}