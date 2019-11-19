package com.lisong.learn.core.interfaces;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.Random;
import java.util.Scanner;

public class Exercise16 {

    public static void main(String[] args) {

        Scanner scan = new Scanner(new LowerCharSequenceAdaptor(100));
        while(scan.hasNext()) {
            System.out.print(scan.next());
        }
    }
}

class LowerCharSequenceAdaptor extends UpperCharSequence implements Readable {

    private int count;

    LowerCharSequenceAdaptor(int count) { this.count = count; }

    @Override
    public int read(CharBuffer cb) throws IOException {
        if(count-- == 0)
            return -1;
        cb.append(next());
        return 1;
    }
}

class LowerCharSequence {

    protected Random rand = new Random(47);

    char next() {
        return (char)('a' + rand.nextInt(26));
    }
}

class UpperCharSequence extends LowerCharSequence {

    @Override
    char next() {
        return (char)('A' + rand.nextInt(26));
    }
}