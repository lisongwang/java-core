package com.lisong.learn.core.innerclasses;

import com.lisong.learn.core.innerclasses.facade.Selector;
import com.lisong.learn.core.innerclasses.facade.Sequence;

import static com.lisong.learn.core.util.Print.print;

public class Exercise22 {

    public static void main(String[] args) {

        Sequence<String> seq = new Sequence<>(5);
        seq.addItem("First Object");
        seq.addItem("Second Object");
        seq.addItem("Third Object");
        seq.addItem("Fourth Object");

        Selector<String> sel = seq.getInverseSelector();
        while(!sel.end()) {
            print(sel.current());
            sel.next();
        }
    }
}
