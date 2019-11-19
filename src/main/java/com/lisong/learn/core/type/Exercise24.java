package com.lisong.learn.core.type;

import com.lisong.learn.core.type.factory.Part;
import com.lisong.learn.core.util.Null;

import static com.lisong.learn.core.util.Print.printnb;

public class Exercise24 {

    public static void main(String[] args) {

        for(int i = 0; i < 100; i++) {
            Part p = Part.randomPart();
            if(p instanceof Null)
                printnb( p + " ");
        }
    }
}
