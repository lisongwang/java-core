package com.lisong.learn.core.interfaces.facade;

import static com.lisong.learn.core.util.Print.print;

public class Apply {

    public static void apply(Processor p, Object s) {

        print("Using Processor " + p.name());
        print(p.process(s));
    }
}
