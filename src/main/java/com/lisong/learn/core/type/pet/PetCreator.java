package com.lisong.learn.core.type.pet;

import java.util.*;

public abstract class PetCreator {

    private Random rand = new Random(47);
    abstract List<Class<? extends Pet>> types();

    public Pet randomPet() {
        try {
            return types().get(rand.nextInt(types().size())).newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public Pet[] arrayPets(int size) {

        Pet[] pets = new Pet[size];
        for(int i = 0; i < size; i++)
            pets[i] = randomPet();
        return pets;
    }

    public List<Pet> listPets(int size) {
        List<Pet> listPets = new ArrayList<>();
        Collections.addAll(listPets, arrayPets(size));
        return listPets;
    }
}
