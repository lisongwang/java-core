package com.lisong.learn.core.io;

import java.io.*;

public class Exercise21 {

    public static void main(String[] args) {
        if(args.length < 1) {
            System.err.println("Provide the file name!");
            System.exit(0);
        }
        try {
            System.setIn(new FileInputStream(new File(args[0])));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            while((line = br.readLine()) != null)
                System.out.println(line.toUpperCase());
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}