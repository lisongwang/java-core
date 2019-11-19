package com.lisong.learn.core.operators;

import java.util.Random;

import static com.lisong.learn.core.util.Print.printBinaryInt;
import static com.lisong.learn.core.util.Print.printBinaryLong;

public class BitManipulation {

    public static void main(String[] args) {

        Random rand = new Random(207);

        int i = rand.nextInt();
        int j = rand.nextInt();

        printBinaryInt("-1 ", -1);
        printBinaryInt("1 ", +1);

        printBinaryInt("maxpos ", Integer.MAX_VALUE);
        printBinaryInt("maxneg ", Integer.MIN_VALUE);

        printBinaryInt("i ", i);
        printBinaryInt("~i ", ~i);
        printBinaryInt("-i ", -i);
        printBinaryInt("j ", j);
        printBinaryInt("i&j ", i&j);
        printBinaryInt("i|j ", i|j);
        printBinaryInt("i^j ", i^j);
        printBinaryInt("i<<5 ", i<<5);
        printBinaryInt("i>>5 ", i>>5);
        printBinaryInt("~i>>5 ", ~i>>5);
        printBinaryInt("i>>>5 ", i>>>5);
        printBinaryInt("~i>>>5 ", ~i>>>5);

        long l = rand.nextLong();
        long m = rand.nextLong();

        printBinaryLong("-1L ", -1L);
        printBinaryLong("1L ", +1L);

        printBinaryLong("maxpos ", Long.MAX_VALUE);
        printBinaryLong("maxneg ", Long.MIN_VALUE);

        printBinaryLong("l ", l);
        printBinaryLong("~l ", ~l);
        printBinaryLong("-l ", -l);
        printBinaryLong("m ", m);
        printBinaryLong("l&m ", l&m);
        printBinaryLong("l|m ", l|m);
        printBinaryLong("l^m ", l^m);
        printBinaryLong("l<<5 ", l<<5);
        printBinaryLong("l>>5 ", l>>5);
        printBinaryLong("~l>>5 ", ~l>>5);
        printBinaryLong("l>>>5 ", l>>>5);
        printBinaryLong("~l>>>5 ", ~l>>>5);
    }
}