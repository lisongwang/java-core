package com.lisong.learn.core.generics;

import com.lisong.learn.core.type.factory.Part;
import com.lisong.learn.core.type.pet.Pet;
import com.lisong.learn.core.type.pet.Pets;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.lisong.learn.core.util.Print.print;
import static com.lisong.learn.core.util.Print.printnb;

public class Exercise6 {

    private static class RandomList<T> {

        private List<T> items = new ArrayList<>();

        private Random rand = new Random(155);

        void add(T t) { items.add(t); }

        T select() { return items.get(rand.nextInt(items.size())); }
    }

    public static void main(String[] args) {

        RandomList<Pet> petList = new RandomList<>();
        Pets pets = new Pets();
        for(int i = 0; i < 10; i++) {
            Pet p = pets.randomPet();
            printnb(p + " ");
            petList.add(p);
        }
        print();
        for(int i = 0; i < 10; i++)
            printnb(petList.select() + " ");
        print();
        RandomList<Part> partList = new RandomList<>();
        for(int i = 0; i < 10; i++) {
            Part p = Part.randomPart();
            printnb(p + " ");
            partList.add(p);
        }
        print();
        for(int i = 0; i < 10; i++)
            printnb(partList.select() + " ");
        print();
    }
}
