package com.lisong.learn.core.type.pet;

import static com.lisong.learn.core.util.Print.print;

public class Cat extends Pet {
    Cat(String name) {
        super(name);
    }

    public Cat() {
        super();
    }

    public void speak() {
        print("Mu~");
    }
}
