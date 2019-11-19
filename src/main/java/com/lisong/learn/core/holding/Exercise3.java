package com.lisong.learn.core.holding;

import com.lisong.learn.core.innerclasses.facade.Selector;
import com.lisong.learn.core.innerclasses.facade.Sequence;
import com.lisong.learn.core.innerclasses.facade.UnlimitedSequence;

import static com.lisong.learn.core.util.Print.print;

public class Exercise3 {

    public static void main(String[] args) {

        Sequence<String> seq = new UnlimitedSequence<>();

        for(int i = 0; i < 100; i++)
            seq.addItem("Object " + i);

        Selector<String> sel = seq.getSelector();
        while(!sel.end()) {
            print(sel.current());
            sel.next();
        }
    }
}
