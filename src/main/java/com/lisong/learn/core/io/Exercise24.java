package com.lisong.learn.core.io;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;

import static com.lisong.learn.core.util.Print.print;

public class Exercise24 {

    public static void main(String[] args) {
        ByteBuffer bf = ByteBuffer.allocate(1024);
        DoubleBuffer db = bf.asDoubleBuffer();
        db.put(new double[]{10.5,5.1,34,68.22,99,75.0968,21});
        print("ib.get(3): " + db.get(3));
        db.put(3, 1000);
        db.flip();
        db.put(999.999);
        db.rewind();
        while(db.hasRemaining())
            print(db.get());
    }
}