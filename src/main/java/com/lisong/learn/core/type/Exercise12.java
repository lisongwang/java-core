package com.lisong.learn.core.type;

import com.lisong.learn.core.generics.coffee.Coffee;
import com.lisong.learn.core.generics.coffee.CoffeeGenerator;
import com.lisong.learn.core.util.TypeCount;

import static com.lisong.learn.core.util.Print.print;
import static com.lisong.learn.core.util.Print.printnb;

public class Exercise12 {

    public static void main(String[] args) {

        TypeCount coffeeCount = new TypeCount(Coffee.class);
        CoffeeGenerator coffeeGen = new CoffeeGenerator(10);
        for(Coffee c : coffeeGen) {
            printnb(c + ", ");
            coffeeCount.count(c);
        }
        print();
        print(coffeeCount);
    }
}
