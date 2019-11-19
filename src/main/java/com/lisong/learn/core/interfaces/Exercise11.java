package com.lisong.learn.core.interfaces;

import com.lisong.learn.core.interfaces.facade.Apply;
import com.lisong.learn.core.interfaces.facade.Processor;

/**
 * Show how Adaptor pattern works.
 */
public class Exercise11 {

    public static void main(String[] args) {

        Apply.apply(new SwapAdaptor(new Swap()), "this is a test string");
    }
}

class SwapAdaptor implements Processor {

    Swap swap;

    SwapAdaptor(Swap swap) {
        this.swap = swap;
    }

    @Override
    public String name() {
        return swap.getClass().getSimpleName();
    }

    @Override
    public Object process(Object input) {
        return swap.swap((String)input);
    }
}

class Swap {

    String swap(String input) {

        char[] charArray = input.toCharArray();
        for(int i = 0; i < charArray.length; i++) {
            if(i%2 == 1) {
                //swap current index with previously one
                char temp = charArray[i];
                charArray[i] = charArray[i-1];
                charArray[i-1] = temp;
            }
        }
        return new String(charArray);
    }
}
