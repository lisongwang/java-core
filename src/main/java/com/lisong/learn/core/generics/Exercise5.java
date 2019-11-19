package com.lisong.learn.core.generics;

import com.lisong.learn.core.type.factory.Part;
import com.lisong.learn.core.util.LinkedStack;

import static com.lisong.learn.core.util.Print.print;
import static com.lisong.learn.core.util.Print.printnb;

public class Exercise5 {

    public static void main(String[] args) {

        LinkedStack<Part> stack = new LinkedStack<>();
        for(int i = 0; i < 10; i++) {
            Part p = Part.randomPart();
            printnb(p + " ");
            stack.push(p);
        }
        print();
        while(!stack.isEmpty())
            printnb(stack.pop() + " ");
    }
}
