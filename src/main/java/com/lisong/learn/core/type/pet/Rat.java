package com.lisong.learn.core.type.pet;

public class Rat extends Rodent {
    Rat(String name) {
        super(name);
    }

    Rat() {
        super();
    }
    static class Factory implements com.lisong.learn.core.util.Factory<Rat> {
        @Override
        public Rat create() {
            return new Rat();
        }
    }
}
