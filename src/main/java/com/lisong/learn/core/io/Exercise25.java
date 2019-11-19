package com.lisong.learn.core.io;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static com.lisong.learn.core.util.Print.print;

public class Exercise25 {
    private static final int BSIZE = 1024*1024;

    private abstract static class Tester {
        protected String name;
        Tester(String name) {
            this.name = name;
        }

        abstract ByteBuffer test();
        abstract String getName();
        void runTest() {
            print(getName());
            Instant in1 = Instant.now();
            ByteBuffer bf = test();
            Instant in2 = Instant.now();
            print("duration in startup: " + Duration.between(in1,in2).toMillis());
            IntBuffer ib = bf.asIntBuffer();
            for(int i = 0; i < 40000; i++)
                ib.put(i);
            Instant in3 = Instant.now();
            print("duration in work: " + Duration.between(in1,in3).toMillis());
        }
    }

    private static List<Tester> tests = new ArrayList<>();
    static {
        tests.add(new Tester("ByteBuffer.allocate") {
            @Override
            ByteBuffer test() {
                return ByteBuffer.allocate(BSIZE);
            }

            @Override
            String getName() {
                return name;
            }
        });

        tests.add(new Tester("ByteBuffer.allocateDirect") {
            @Override
            ByteBuffer test() {
                return ByteBuffer.allocateDirect(BSIZE);
            }

            @Override
            String getName() {
                return name;
            }
        });
    }

    public static void main(String[] args) {
        for(Tester t : tests)
            t.runTest();
    }
}