package com.lisong.learn.core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.lisong.learn.core.util.Print.print;

public class Exercise26 {

    private static String[] readLines(File file) {
        try (FileChannel fc = new FileInputStream(file).getChannel()) {
            MappedByteBuffer mbf = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
            return Charset.defaultCharset().decode(mbf).toString().split("\n");
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args)  {
        if(args.length < 2) {
            System.err.println("Please input file and regex!");
            System.exit(0);
        }
        Pattern p = Pattern.compile(args[1]);
        int index = 0;
        Matcher m = p.matcher("");
        for(String line : readLines(new File(args[0]))) {
            m.reset(line);
            index++;
            while(m.find())
                print(index + ": " + m.group() + ": " + m.start());
        }
    }
}