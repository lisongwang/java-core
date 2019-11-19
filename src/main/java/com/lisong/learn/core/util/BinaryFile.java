package com.lisong.learn.core.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class BinaryFile {

    public static byte[] read(File file) throws IOException {
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file))) {
            byte[] result = new byte[in.available()];
            in.read(result);
            return result;
        }
    }

    public static byte[] read(String fileName) throws IOException {
        return read(new File(fileName).getAbsoluteFile());
    }
}
