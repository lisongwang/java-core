package com.lisong.learn.core.arrays;

import com.lisong.learn.core.util.CountingGenerator;

import static com.lisong.learn.core.util.Print.print;

public class Exercise13 {

    private static String fillStringWithSequence(char startLetter, int length) {
        CountingGenerator.Character charGen = new CountingGenerator.Character();
        char[] result = new char[length];
        boolean startFlag = false;
        int count = 0;
        while(true) {
            if(count >= length)
                break;
            Character c = charGen.next();
            if(!startFlag && c.equals(startLetter))
                startFlag = true;

            if(startFlag)
                result[count++] = c;
        }
        return new String(result);
    }

    public static void main(String[] args) {

        print(fillStringWithSequence('h', 10));
        print(fillStringWithSequence('d', 20));
        print(fillStringWithSequence('x', 15));
        print(fillStringWithSequence('a', 26));
    }
}
