package com.lisong.learn.core.io;

import com.lisong.learn.core.util.BinaryFile;
import com.lisong.learn.core.util.Directory;

import java.io.File;
import java.io.IOException;

import static com.lisong.learn.core.util.Print.print;

public class Exercise20 {

    private static boolean equalsWithMagicNum(byte[] bytes) {
        int i1 = Byte.toUnsignedInt(bytes[0]);
        int i2 = Byte.toUnsignedInt(bytes[1]);
        int i3 = Byte.toUnsignedInt(bytes[2]);
        int i4 = Byte.toUnsignedInt(bytes[3]);
        return i1 == 0xCA && i2 == 0xFE && i3 == 0xBA && i4 == 0xBE;
    }

    public static void main(String[] args) {
        boolean flag = true;
        for(File file : Directory.walk(".", ".*\\.class").files) {
            try {
                byte[] bytes = BinaryFile.read(file);
                if(!equalsWithMagicNum(bytes)) {
                    print("File: " + file.getName() + " does not begin with 'CAFEBABE'");
                    flag = false;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(flag)
            print("All class files begin with 'CAFEBABE'");
    }
}