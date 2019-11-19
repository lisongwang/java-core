package com.lisong.learn.core.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.lisong.learn.core.util.Print.print;

public class TextFile {

    private static final String WORD_REGEX = "(?m)(\\b\\w+\\b)";

    public static String readFile(File file) {

        StringBuilder sb = new StringBuilder();

        try (BufferedReader rb = new BufferedReader(new FileReader(file))) {
            String line;
            while((line = rb.readLine()) != null) {
                sb.append(line);
            }
        }catch(IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    public static String[] readLines(File file) {

        List<String> lines = new ArrayList<>();

        try (BufferedReader rb = new BufferedReader(new FileReader(file))) {
            String line;
            while((line = rb.readLine()) != null) {
                lines.add(line);
            }
        }catch(IOException e) {
            e.printStackTrace();
        }

        return lines.toArray(new String[0]);
    }

    public static List<String> readWords(File file) {

        List<String> wordList = new ArrayList<>();
        Matcher m = Pattern.compile(WORD_REGEX).matcher(TextFile.readFile(file));
        while(m.find())
            wordList.add(m.group());
        return wordList;
    }

    public static List<File> collectFiles(String path) {

        List<File> singleFiles = new ArrayList<>();
        readFile(singleFiles, new File(path));
        return singleFiles;
    }

    public static void listFiles(String path, PrintStream out) {

        List<File> list = collectFiles(path);
        out.println("All the files under path: \n" + path + "\n----------------------------");
        int lines = 0;
        for(File f: list) {
            int line = TextFile.readLines(f).length;
            print(f.getName() + "   Lines: " + line);
            lines += line;
        }
        out.println();
        out.println("Total files: " + list.size() + "    total lines: " + lines);
    }

    private static void readFile(List<File> singleFiles, File file) {

        if(file.isDirectory()) {
            File[] files = file.listFiles();
            for(File f : files) {
                readFile(singleFiles, f);
            }
        }else {
            singleFiles.add(file);
        }
    }
}