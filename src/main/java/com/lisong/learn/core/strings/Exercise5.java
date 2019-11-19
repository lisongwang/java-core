package com.lisong.learn.core.strings;

import java.math.BigInteger;
import java.util.Formatter;

public class Exercise5 {

    public static void main(String[] args) {

        StringBuilder sb = new StringBuilder();
        Formatter format = new Formatter(sb);

        char c = 'A';
        int d = 1250;
        BigInteger w = new BigInteger("500000000000000");
        double f = 128.6536;
        Exercise5 exe5 = new Exercise5();
        String s = "end!";

        format.format("c = %1$-12c\nd = %2$-12d\nw = %3$-12x\nf = %4$-12.2f\nf = %4$-12.3e\n" +
                "exe5 = %5$-12h\nb = %6$-12b\ns = %7$-12.4s\n%%", c, d, w, f, exe5, false, s);
        System.out.print(sb);
    }
}
