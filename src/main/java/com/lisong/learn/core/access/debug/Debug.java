package com.lisong.learn.core.access.debug;

import static com.lisong.learn.core.util.Print.print;

public class Debug {

    public static void debug(String... args) {
        print("debug info: ----------------------------------------");
        for (String s : args) {
            print(s);
        }
        print("debug info: ----------------------------------------");
    }
}
