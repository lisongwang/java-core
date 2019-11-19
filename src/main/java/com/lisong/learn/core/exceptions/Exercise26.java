package com.lisong.learn.core.exceptions;

import java.io.FileInputStream;

public class Exercise26 {

    public static void main(String[] args) throws Exception {

        FileInputStream file = new FileInputStream("Main.java");
        file.close();
    }
}
