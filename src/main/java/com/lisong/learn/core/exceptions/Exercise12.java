package com.lisong.learn.core.exceptions;

import com.lisong.learn.core.innerclasses.facade.Sequence;
import com.lisong.learn.core.innerclasses.facade.SequenceOverflowException;

public class Exercise12 {

    public static void main(String[] args) {

        Sequence<String> seq = new Sequence<>(10);
        try {
            while(true)
                seq.addItemWithException("Object");
        }catch(SequenceOverflowException e) {
            System.out.println("Two many elements added!");
            e.printStackTrace();
        }
        seq.addItem("Object");
    }
}
