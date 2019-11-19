package com.lisong.learn.core.type;

import static com.lisong.learn.core.util.Print.print;

public class Exercise10 {

    public static void main(String[] args) {

        char[] charArray = new char[10];
        print("char[] has the same Class object with primitive char? " + charArray.getClass().equals(char.class));
        print("char[]'s super class is: " + charArray.getClass().getSuperclass());
        print("char[] is instance of Object? " + (charArray instanceof Object));
    }
}
