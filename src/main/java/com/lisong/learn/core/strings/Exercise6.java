package com.lisong.learn.core.strings;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static com.lisong.learn.core.util.Print.print;

public class Exercise6 {

    private int i = 1025;
    private long l = 500000000;
    private double d = 128.6532d;
    private float f = 25.48f;

    @Override
    public String toString() {
        return String.format("i = %1$05d\nl = %2$-12s\nd = %3$-12.2f\nd = %3$-12.3e\nf = %4$-12.1f\nhex = %5$-12h\n", i, l, d, f, this);
    }

    private static void showHexFile() {
        File file = new File("JavaCore/src/main/java/com/lisong/learn/core/strings/Exercise6.java");
        try (BufferedInputStream bf = new BufferedInputStream(new FileInputStream(file))){
            byte[] des = new byte[1024];
            bf.read(des, 0, des.length);
            print(Hex.format(des));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //print(new Exercise6());
        showHexFile();
    }
}

class Hex {

    static String format(byte[] data) {

        StringBuilder sb = new StringBuilder();
        int n = 0;
        for(byte b : data) {
            if(n % 16 == 0)
                sb.append(String.format("%05x ", n));
            sb.append(String.format("%02x ", b));
            n++;
            if(n % 16 == 0)
                sb.append("\n");
        }
        return sb.toString();
    }
}