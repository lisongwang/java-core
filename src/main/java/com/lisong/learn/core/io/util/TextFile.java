package com.lisong.learn.core.io.util;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class TextFile extends ArrayList<String> {

    public static String read(File file) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while((line = br.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public static void write(File file, String text) throws IOException {
        try (PrintWriter out = new PrintWriter(file)){
            out.println(text);
        }
    }

    public void write(String fileName) throws IOException {
        try (PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile())){
            for(String line : this)
                out.println(line);
        }
    }

    public TextFile(String fileName, String splitter) throws IOException {
        super(Arrays.asList(read(new File(fileName).getAbsoluteFile()).split(splitter)));
        if(get(0).equals(""))
            remove(0);
    }

    public TextFile(String fileName) throws IOException {
        this(fileName, "\n");
    }
}