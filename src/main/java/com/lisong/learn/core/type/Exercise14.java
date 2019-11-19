package com.lisong.learn.core.type;

import com.lisong.learn.core.type.factory.Part;

import static com.lisong.learn.core.util.Print.printnb;

public class Exercise14 {

    public static void main(String[] args) {

        for(int i = 0; i < 20; i++)
            printnb(Part.randomPart1() + " ");
    }
}
