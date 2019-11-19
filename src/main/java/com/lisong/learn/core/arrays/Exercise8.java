package com.lisong.learn.core.arrays;

import java.util.ArrayList;
import java.util.List;

import static com.lisong.learn.core.util.Print.print;

public class Exercise8 {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        String[] sArray = new String[3];
        sArray[0] = "String is ok";
        //sArray[1] = 'a';
        Object[] oArray = new Object[4];
        oArray[0] = "String still ok";
        oArray[1] = 'a';
        oArray[2] = 10;
        oArray[3] = new BerylliumSphere();

        print("oArray[0] " + oArray[0].getClass().getSimpleName());
        print("oArray[1] " + oArray[1].getClass().getSimpleName());
        print("oArray[2] " + oArray[2].getClass().getSimpleName());
        print("oArray[3] " + oArray[3].getClass().getSimpleName());

        oArray = sArray;
        print("oArray " + oArray.getClass().getSimpleName());
        //oArray[2] = 10; //runtime exception now.

        List<String>[] lArray = new List[10];
        lArray[0] = new ArrayList<>();
        print("lArray[0] " + lArray[0].getClass().getSimpleName());
        print("lArray " + lArray.getClass().getSimpleName());
    }
}
