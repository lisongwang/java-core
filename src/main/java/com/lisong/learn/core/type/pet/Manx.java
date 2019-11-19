package com.lisong.learn.core.type.pet;

public class Manx extends Cat {
    Manx(String name) {
        super(name);
    }

    Manx() {
        super();
    }
    static class Factory implements com.lisong.learn.core.util.Factory<Manx> {
        @Override
        public Manx create() {
            return new Manx();
        }
    }
}
