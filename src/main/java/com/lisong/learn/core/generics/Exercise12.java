package com.lisong.learn.core.generics;

import com.lisong.learn.core.generics.coffee.Coffee;
import com.lisong.learn.core.type.pet.Pet;
import com.lisong.learn.core.type.pet.Pets;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.lisong.learn.core.util.Print.print;

/**
 * Parameter Type inference can't prevent you from ClassCastException at runtime.
 */
public class Exercise12 {

    static <K,V> Map<K,V> map() { return new LinkedHashMap<>(); }

    static void f(Map<Pet, Coffee> m) {
        for(Map.Entry<Pet, Coffee> entry : m.entrySet()) {
            Pet p = entry.getKey();
            Coffee c = entry.getValue();
            print( p + " = " + c);
        }
    }

    public static void main(String[] args) {

        Map<Pet, Coffee> m = map();
        m.put(new Pets().randomPet(), Coffee.randomCoffee());
        m.put(new Pets().randomPet(), Coffee.randomCoffee());
        m.put(new Pets().randomPet(), Coffee.randomCoffee());
        f(m);

        Map m1 = map();
        m1.put("text", 1);
        m1.put("value", 2);
        m1.put("thing", 3);
        f(m1);

        f(map());
    }
}
