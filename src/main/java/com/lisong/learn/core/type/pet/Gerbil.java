package com.lisong.learn.core.type.pet;

public class Gerbil extends Rodent {
    Gerbil(String name) {
        super(name);
    }

    Gerbil() {
        super();
    }
    static class Factory implements com.lisong.learn.core.util.Factory<Gerbil> {
        @Override
        public Gerbil create() {
            return new Gerbil();
        }
    }
}
