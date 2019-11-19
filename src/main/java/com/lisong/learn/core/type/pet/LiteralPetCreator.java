package com.lisong.learn.core.type.pet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LiteralPetCreator extends PetCreator {

    private static List<Class<? extends Pet>> types = new ArrayList<>();

    static {
        Collections.addAll(types,
                Cymric.class,
                EgyptianMau.class,
                Hamster.class,
                Manx.class,
                Mouse.class,
                Mutt.class,
                Pug.class,
                Rat.class,
                Gerbil.class);
    }

    @Override
    public List<Class<? extends Pet>> types() {
        return types;
    }
}
