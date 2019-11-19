package com.lisong.learn.core.type;

import com.lisong.learn.core.util.ShowMethods;

import static com.lisong.learn.core.util.Print.print;

public class Exercise17 {

    public static void main(String[] args) {

        if(args.length < 1) {
            print("Usage java Exercise17 com.lisong.learn.core.type.Exercise17");
            System.exit(0);
        }

        try {
            ShowMethods.extractMethodsWithoutFinalAndNative(Class.forName(args[0]), System.out);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
