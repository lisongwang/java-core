package com.lisong.learn.core.generics;

import com.lisong.learn.core.generics.coffee.Coffee;
import com.lisong.learn.core.util.LinkedStack;

import static com.lisong.learn.core.util.Print.print;

/**
 * Parameter Type inference works better after jdk1.7.
 */
public class Exercise11 {

    static <T> LinkedStack<T> lStack() { return new LinkedStack<>(); }

    public static void main(String[] args) {

        LinkedStack<Coffee> ls = lStack();
        //ls.push("coffee"); Can't push anything except Coffee to this stack
        ls.push(Coffee.randomCoffee());
        ls.push(Coffee.randomCoffee());
        ls.push(Coffee.randomCoffee());
        while(!ls.isEmpty())
            print(ls.pop());
    }
}