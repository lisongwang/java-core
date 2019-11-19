package com.lisong.learn.core.enumerated;

import java.util.Random;

import static com.lisong.learn.core.enumerated.enums.LowChar.*;
import static com.lisong.learn.core.util.Print.print;
import static com.lisong.learn.core.util.Print.printnb;

/**
 * Combine exercise 5, exercise 6.
 */
public class Exercise6 {

    public static void main(String[] args) {
        Random rand = new Random(108);
        for(int i = 0; i < 100; i++) {
            int c = rand.nextInt(26) + 'a';
            printnb((char)c + ", " + c + ": ");
            if(VOWEL.contains(c))
                print(VOWEL);
            else if(SOMETIMES_A_VOWEL.contains(c))
                print(SOMETIMES_A_VOWEL);
            else if(CONSONANT.contains(c))
                print(CONSONANT);
            else
                print("Incorrect char");
        }
    }
}