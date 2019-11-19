package com.lisong.learn.core.type;

import com.lisong.learn.core.type.factory.Part;
import com.lisong.learn.core.util.TypeCount;

import static com.lisong.learn.core.util.Print.print;
import static com.lisong.learn.core.util.Print.printnb;

public class Exercise13 {

    public static void main(String[] args) {

        TypeCount count = new TypeCount(Part.class);
        for(int i = 0; i < 20; i++) {
            Part p = Part.randomPart();
            printnb(p.getClass().getSimpleName() + " ");
            count.count(p);
        }
        print("");
        print(count);
    }
}
