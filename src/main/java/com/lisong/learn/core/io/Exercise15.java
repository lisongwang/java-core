package com.lisong.learn.core.io;

import java.io.*;

import static com.lisong.learn.core.util.Print.print;

public class Exercise15 {

    private static void writeData(File file) {
        try (DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
            out.writeBoolean(true);
            out.writeByte(127);
            out.writeChar('C');
            out.writeDouble(12.6);
            out.writeFloat(15.2f);
            out.writeInt(50000);
            out.writeLong(1000000000);
            out.writeShort(32000);
            out.writeUTF("End!");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readData(File file) {
        try (DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(file)))) {
            print(in.readBoolean());
            print(in.readByte());
            print(in.readChar());
            print(in.readDouble());
            print(in.readFloat());
            print(in.readInt());
            print(in.readLong());
            print(in.readShort());
            print(in.readUTF());
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
    }
}