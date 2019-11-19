package com.lisong.learn.core.generics;

import com.lisong.learn.core.type.pet.Pet;
import com.lisong.learn.core.type.pet.Pets;

import static com.lisong.learn.core.util.Print.print;

public class Exercise1 {

    public static void main(String[] args) {

        Holder<Pet> petHolder = new Holder<>(new Pets().randomPet());
        print(petHolder.getObj());
        petHolder.setObj(new Pets().randomPet());
        print(petHolder.getObj());
    }
}

class Holder<T> {

    private T obj;
    Holder(T obj) { this.obj = obj; }
    T getObj() { return this.obj; }
    void setObj(T obj) { this.obj = obj; }

    @Override
    public String toString() {
        return obj.toString();
    }

    @Override
    public boolean equals(Object o) {
        return obj.equals(o);
    }
}
