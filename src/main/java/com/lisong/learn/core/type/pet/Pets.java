package com.lisong.learn.core.type.pet;

import java.util.List;

/**
 * Pet Creator facade
 */
public class Pets {

    private PetCreator creator = new FactoryPetCreator();

    public Pet[] arrayPets(int size) {
        return creator.arrayPets(size);
    }

    public List<Pet> listPets(int size) {
        return creator.listPets(size);
    }

    public Pet randomPet() {
        return creator.randomPet();
    }
}
