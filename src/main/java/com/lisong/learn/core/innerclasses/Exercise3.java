package com.lisong.learn.core.innerclasses;

import com.lisong.learn.core.innerclasses.facade.Selector;
import com.lisong.learn.core.innerclasses.facade.Sequence;

/**
 * Combine exercise 1, exercise 2, exercise 3.
 */
public class Exercise3 {

    public static void main(String[] args) {
        Outer3.Inner inner = new Outer3("testOuter").getInner();
        System.out.println(inner);

        Sequence<StringHolder> seq = new Sequence<>(5);
        seq.addItem(new StringHolder("I am a string"));
        seq.addItem(new StringHolder("I am a boy"));
        seq.addItem(new StringHolder("I am working"));
        seq.addItem(new StringHolder("I am study"));
        seq.addItem(new StringHolder("I am happy"));

        Selector<StringHolder> sel = seq.getSelector();
        while(!sel.end()) {
            System.out.println(sel.current());
            sel.next();
        }
    }
}

class Outer3 {

    private String s;

    Outer3(String s) { this.s = s; }

    class Inner {
        @Override
        public String toString() {
            return s;
        }
    }

    Inner getInner() {
        return new Inner();
    }
}

class StringHolder {

    private String text;

    StringHolder(String text) { this.text = text; }

    @Override
    public String toString() {
        return text;
    }
}