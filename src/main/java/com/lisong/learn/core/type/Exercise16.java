package com.lisong.learn.core.type;

import com.lisong.learn.core.generics.coffee.Coffee;

import static com.lisong.learn.core.util.Print.printnb;

public class Exercise16 {

    public static void main(String[] args) {

        for(int i = 0; i < 15; i++)
            printnb(Coffee.randomCoffee() + ", ");
    }
}
