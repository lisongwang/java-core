package com.lisong.learn.core.type;

import com.lisong.learn.core.type.pet.Pet;
import com.lisong.learn.core.util.TypeCount;
import com.lisong.learn.core.type.pet.Pets;

import static com.lisong.learn.core.util.Print.print;
import static com.lisong.learn.core.util.Print.printnb;

public class Exercise11 {

    public static void main(String[] args) {

        TypeCount count = new TypeCount(Pet.class);
        for(Pet p : new Pets().arrayPets(20)) {
            printnb(p.getClass().getSimpleName() + " ");
            count.count(p);
        }
        print("");
        print(count);
    }
}
