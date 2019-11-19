package com.lisong.learn.core.type;

import com.lisong.learn.core.util.ShowMethods;

import static com.lisong.learn.core.util.Print.print;

public class Exercise18 {

    public static int i = 0;

    public static void main(String[] args) {

        if(args.length < 2) {
            print("Usage java Exercise18 com.lisong.learn.core.type.ExtractTest String");
            System.exit(0);
        }

        try {
            ShowMethods.extractMethodsWithString(Class.forName(args[0]), System.out, args[1]);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void t() {}
}

class ExtractTest {

    public void f() {}
    public void g() {}
}
