package com.lisong.learn.core.generics;

import com.lisong.learn.core.generics.tuple.SixTuple;
import com.lisong.learn.core.type.factory.Part;
import com.lisong.learn.core.type.pet.Pet;
import com.lisong.learn.core.type.pet.Pets;

import static com.lisong.learn.core.util.Print.print;

public class Exercise3 {

    public static void main(String[] args) {

        SixTuple<Pet, Part, Part, String, Integer, Double> tuple = new SixTuple<>(
                new Pets().randomPet(), Part.randomPart(), Part.randomPart1(), "Good", 100, 500.20
        );
        print(tuple);
    }
}