package com.lisong.learn.core.type;

import com.lisong.learn.core.type.pet.Pet;
import com.lisong.learn.core.type.pet.Pets;
import com.lisong.learn.core.util.TypeCount;

import static com.lisong.learn.core.util.Print.print;
import static com.lisong.learn.core.util.Print.printnb;

public class Exercise15 {

    public static void main(String[] args) {

        TypeCount count = new TypeCount(Pet.class);
        for(Pet p : new Pets().listPets(20)) {
            printnb( p + " ");
            count.count(p);
        }
        print("");
        print(count);
    }
}
