package com.lisong.learn.core.type;

import com.lisong.learn.core.util.ClassInfo;

import static com.lisong.learn.core.util.Print.print;

public class Exercise20 {

    public static void main(String[] args) {

        if(args.length < 1) {
            print("Usage java Exercise20 com.lisong.learn.core.type.Exercise20");
            System.exit(0);
        }
        try {
            ClassInfo.extractClass(Class.forName(args[0]), System.out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
