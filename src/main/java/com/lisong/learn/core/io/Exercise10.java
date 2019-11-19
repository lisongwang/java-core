package com.lisong.learn.core.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

import static com.lisong.learn.core.util.Print.print;

/**
 * Combine exercise 7, exercise 8, exercise 9, exercise 10.
 */
public class Exercise10 {

    private static boolean find(String word, String line) {
        return Pattern.compile("(?i)(\\b" + word + "\\b)").matcher(line).find();
    }

    public static void main(String[] args) {
        if(args.length < 2) {
            print("Provide the file name as well as the words!");
            System.exit(0);
        }
        List<String> words = Arrays.asList(args).subList(1, args.length);
        List<String> lines = new LinkedList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(new File(args[0])))) {
            String line;
            while((line = br.readLine()) != null)
                lines.add(line);
        }catch(IOException e) {
            e.printStackTrace();
        }
        ListIterator<String> it = lines.listIterator(lines.size());
        while(it.hasPrevious()) {
            String line = it.previous();
            for(String word : words) {
                if(find(word, line))
                    print(line.toUpperCase());
            }
        }
    }
}