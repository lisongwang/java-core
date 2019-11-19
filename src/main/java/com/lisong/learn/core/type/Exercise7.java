package com.lisong.learn.core.type;

import static com.lisong.learn.core.util.Print.print;

public class Exercise7 {

    public static void main(String[] args) {

        if(args.length < 1) {
            print("Usage: java Exercise7 com.lisong.learn.core.type.Candy");
            System.exit(0);
        }
        try {
            Class c = Class.forName(args[0]);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

class Candy {
    static { print("Loading Candy"); }
}

class Gum {
    static { print("Loading Gum"); }
}

class Cookie {
    static { print("Loading Cookie"); }
}

