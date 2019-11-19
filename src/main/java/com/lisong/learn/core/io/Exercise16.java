package com.lisong.learn.core.io;

import java.io.*;

import static com.lisong.learn.core.util.Print.print;

public class Exercise16 {

    private static void writeData(File file) {
        try (RandomAccessFile out = new RandomAccessFile(file, "rw")) {
            out.writeBoolean(false);
            out.writeByte(127);
            out.writeChar('T');
            out.writeDouble(126.611);
            out.writeFloat(151.2653f);
            out.writeInt(500300);
            out.writeLong(1052100000);
            out.writeShort(32600);
            out.writeUTF("End of the file!");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void rewriteData(File file) {
        try (RandomAccessFile out = new RandomAccessFile(file, "rw")) {
            out.seek(16);
            out.writeInt(5210);
            out.writeLong(6100);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readData(File file) {
        try (RandomAccessFile in = new RandomAccessFile(file, "r")) {
            System.out.println(in.readBoolean());
            System.out.println(in.readByte());
            System.out.println(in.readChar());
            System.out.println(in.readDouble());
            System.out.println(in.readFloat());
            System.out.println(in.readInt());
            System.out.println(in.readLong());
            System.out.println(in.readShort());
            System.out.println(in.readUTF());
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        if(args.length < 1) {
            print("Provide the output file!");
            System.exit(0);
        }
        File file = new File(args[0]);
        writeData(file);
        readData(file);
        rewriteData(file);
        readData(file);
    }
}