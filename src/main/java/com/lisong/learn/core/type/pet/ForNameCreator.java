package com.lisong.learn.core.type.pet;

import java.util.ArrayList;
import java.util.List;

public class ForNameCreator extends PetCreator {

    private static List<Class<? extends Pet>> types = new ArrayList<>();
    private static String[] names = new String[] {
            "com.lisong.learn.core.type.pet.EgyptianMau",
            "com.lisong.learn.core.type.pet.Hamster",
            "com.lisong.learn.core.type.pet.Manx",
            "com.lisong.learn.core.type.pet.Mouse",
            "com.lisong.learn.core.type.pet.Mutt",
            "com.lisong.learn.core.type.pet.Pug",
            "com.lisong.learn.core.type.pet.Cymric",
            "com.lisong.learn.core.type.pet.Rat",
            "com.lisong.learn.core.type.pet.Gerbil",
    };

    @SuppressWarnings("unchecked")
    private static void loadTypes() {
        for(String s : names) {
            try {
                types.add((Class<? extends Pet>)Class.forName(s));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    static {
        loadTypes();
    }

    @Override
    public List<Class<? extends Pet>> types() {
        return types;
    }
}
