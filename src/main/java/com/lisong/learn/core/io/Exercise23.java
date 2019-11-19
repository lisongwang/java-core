package com.lisong.learn.core.io;

import com.lisong.learn.core.io.util.CharBufferUtil;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static com.lisong.learn.core.util.Print.print;

public class Exercise23 {
    private static final int BSIZE = 1024*4;

    private static void testCharBuffer1(String fileName) throws IOException {
        ByteBuffer bf = ByteBuffer.allocate(BSIZE);
        FileChannel fin = new FileInputStream(new File(fileName)).getChannel();
        bf.clear();
        fin.read(bf);
        bf.flip();
        print(Charset.defaultCharset().decode(bf));
        //print(bf.asCharBuffer());
    }

    private static void testCharBuffer2(String fileName) throws IOException {
        ByteBuffer bf = ByteBuffer.allocate(BSIZE);
        FileChannel fout = new FileOutputStream(new File(fileName)).getChannel();
        fout.write(ByteBuffer.wrap("你好！".getBytes(StandardCharsets.UTF_16BE)));
        fout.close();
        FileChannel fin = new FileInputStream(new File(fileName)).getChannel();
        bf.clear();
        fin.read(bf);
        bf.flip();
        print(bf.asCharBuffer());
    }

    private static void testCharBuffer3(String fileName) throws IOException {
        ByteBuffer bf = ByteBuffer.allocate(BSIZE);
        FileChannel fout = new FileOutputStream(new File(fileName)).getChannel();
        bf.asCharBuffer().put("你好！");
        fout.write(bf);
        fout.close();
        FileChannel fin = new FileInputStream(new File(fileName)).getChannel();
        bf.clear();
        fin.read(bf);
        bf.flip();
        CharBufferUtil.print(bf.asCharBuffer(), new PrintWriter(System.out, true));
    }

    public static void main(String[] args) {
        if(args.length < 2) {
            System.err.println("Provide the source file and destination file!");
            System.exit(0);
        }
        try {
            testCharBuffer1(args[0]);
            testCharBuffer2(args[1]);
            testCharBuffer3(args[1]);
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}