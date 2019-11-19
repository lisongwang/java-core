package com.lisong.learn.core.type.pet;

import com.lisong.learn.core.util.Factory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class FactoryPetCreator extends PetCreator {

    private static List<Factory<? extends Pet>> factories = new ArrayList<>();
    private static void loadFactory() {
        Collections.addAll(factories,
                new Cymric.Factory(),
                new EgyptianMau.Factory(),
                new Gerbil.Factory(),
                new Hamster.Factory(),
                new Manx.Factory(),
                new Mouse.Factory(),
                new Mutt.Factory(),
                new Pug.Factory(),
                new Rat.Factory());
    }
    static {
        loadFactory();
    }

    private static Random rand = new Random(600);

    public Pet randomPet() {
        return factories.get(rand.nextInt(factories.size())).create();
    }
    @Override
    List<Class<? extends Pet>> types() {
        return null;
    }
}
