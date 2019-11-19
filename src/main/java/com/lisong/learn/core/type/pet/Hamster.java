package com.lisong.learn.core.type.pet;

public class Hamster extends Rodent {
    Hamster(String name) {
        super(name);
    }

    Hamster() {
        super();
    }
    static class Factory implements com.lisong.learn.core.util.Factory<Hamster> {
        @Override
        public Hamster create() {
            return new Hamster();
        }
    }
}
