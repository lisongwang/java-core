package com.lisong.learn.core.io.util;

import java.io.PrintWriter;
import java.nio.CharBuffer;

public class CharBufferUtil {

    public static void print(CharBuffer cb, PrintWriter out) {
        while(cb.hasRemaining()) {
            char c = cb.get();
            if(c != 0)
                out.print(c);
            else
                break;
        }
        out.println();
    }
}