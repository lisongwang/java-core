package com.lisong.learn.core.type.pet;

import static com.lisong.learn.core.util.Print.print;

public class Dog extends Pet {
    Dog(String name) {
        super(name);
    }

    Dog() {
        super();
    }

    public void speak() {
        print("Wang!");
    }
}
