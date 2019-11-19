package com.lisong.learn.core.generics;

import com.lisong.learn.core.innerclasses.facade.Selector;
import com.lisong.learn.core.innerclasses.facade.Sequence;
import com.lisong.learn.core.type.factory.Part;

import static com.lisong.learn.core.util.Print.print;

public class Exercise4 {

    public static void main(String[] args) {

        Sequence<Part> seq = new Sequence<>(5);
        seq.addItem(Part.randomPart());
        seq.addItem(Part.randomPart());
        seq.addItem(Part.randomPart());
        seq.addItem(Part.randomPart());
        seq.addItem(Part.randomPart());

        Selector<Part> sel = seq.getInverseSelector();
        while(!sel.end()) {
            print(sel.current());
            sel.next();
        }
    }
}
