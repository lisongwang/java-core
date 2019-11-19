package com.lisong.learn.core.io;

import java.io.*;
import java.util.Arrays;
import java.util.Random;

import static com.lisong.learn.core.util.Print.print;

public class Exercise27 {

    private static void readObject(String fileName) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            Serial1 s1 = (Serial1)in.readObject();
            print(s1);
        }catch (IOException e) {
            throw new RuntimeException(e);
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void writeObject(String fileName) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            Serial1 s1 = new Serial1();
            print(s1);
            out.writeObject(s1);
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        if(args.length < 1) {
            System.err.println("Provide the store file!");
            System.exit(0);
        }
        writeObject(args[0]);
        readObject(args[0]);
    }
}

class Serial1 implements Serializable {
    private Serial2 s2 = new Serial2();
    private Serial2 s3 = new Serial2("Serial3");
    private double d = 12.12;
    Serial1() { print("invoke Serial1 constructor"); }

    @Override
    public String toString() {
        return s2 + "\n" + s3 + "\n" + d;
    }
}

class Serial2 implements Serializable {
    private Random rand = new Random(68);
    private int[] ints = {rand.nextInt(100), rand.nextInt(100), rand.nextInt(100)};
    private String s = "Serial2";

    Serial2(String s) {
        print("invoke Serial2 constructor");
        this.s = s;
    }
    Serial2() { print("invoke Serial2 constructor"); }
    @Override
    public String toString() {
        return Arrays.toString(ints) + "\n" + s;
    }
}