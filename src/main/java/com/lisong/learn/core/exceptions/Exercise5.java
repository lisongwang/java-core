package com.lisong.learn.core.exceptions;

import static com.lisong.learn.core.util.Print.print;

public class Exercise5 {

    public static void main(String[] args) {

        int i = 20;
        int[] ints = new int[10];
        while(true) {
            try {
                print(ints[i]);
                break;
            }catch(ArrayIndexOutOfBoundsException e) {
                print("Caught exception here!");
                i--;
            }finally {
                print("Are we done yet?");
            }
        }
        print("Now, we are done!");
    }
}
