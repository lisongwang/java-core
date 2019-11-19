package com.lisong.learn.core.operators;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;

/**
 * 1. Wide & Narrowing conversion
 * 2. Max value of byte, short, char
 * 3. Truncate and Round
 * 4. BigInteger and BigDecimal
 */
public class Arithmetic {

    public static void main(String[] args) {

        int i = 122;
        short s = 123;
        s >>= 5;
        s = (short)(s >> 5);
        float f = 2.511f;
        System.out.println("truncate: " + (int)f + " round: " + Math.round(f));

        char c = 'A';
        char c1 = (char)+c;
        char c2 = (char)-c;
        System.out.println("c1: " + Integer.toBinaryString(c1) + " c2: " + Integer.toBinaryString(c2));

        int t1 = 0XBCEFBCEF;
        char c3 = (char) t1;
        System.out.println("c3: " + Integer.toBinaryString(c3));

        int t5 = Character.MAX_VALUE;
        System.out.println("Max value of Char: " + t5);

        float fff = 32.1345f;
        float fff1 = 8.3f;
        BigDecimal bd = new BigDecimal(Float.toString(fff));
        BigDecimal bd1 = new BigDecimal(Float.toString(fff1));
        System.out.println("sum of float: " + (bd.add(bd1)));

        int k = 0;
        StringBuffer bigInteger = new StringBuffer();
        while(k++ < 32) {
            bigInteger.append(new Random().nextInt(10));
        }
        //Long lef = new Long(bigInteger.toString()); //long can't accept two big integer!
        BigInteger bi1 = new BigInteger(bigInteger.toString());
        System.out.println("big integer: " + bi1);
    }
}
