package com.lisong.learn.core.generics;

import com.lisong.learn.core.type.pet.Pet;
import com.lisong.learn.core.type.pet.Pets;

import static com.lisong.learn.core.util.Print.print;

public class Exercise2 {

    public static void main(String[] args) {
        Pets pets = new Pets();
        Holder2<Pet> petHolder = new Holder2<>(pets.randomPet(), pets.randomPet(), pets.randomPet());
        print(petHolder.getT1());
        print(petHolder.getT2());
        print(petHolder.getT3());
    }
}

class Holder2<T> {

    private T t1;
    private T t2;
    private T t3;

    public Holder2(T t1, T t2, T t3) {
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
    }

    public T getT1() {
        return t1;
    }

    public void setT1(T t1) {
        this.t1 = t1;
    }

    public T getT2() {
        return t2;
    }

    public void setT2(T t2) {
        this.t2 = t2;
    }

    public T getT3() {
        return t3;
    }

    public void setT3(T t3) {
        this.t3 = t3;
    }
}
