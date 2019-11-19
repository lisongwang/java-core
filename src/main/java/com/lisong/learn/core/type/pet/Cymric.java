package com.lisong.learn.core.type.pet;

public class Cymric extends Manx {
    Cymric(String name) {
        super(name);
    }

    Cymric() {
        super();
    }
    static class Factory implements com.lisong.learn.core.util.Factory<Cymric> {
        @Override
        public Cymric create() {
            return new Cymric();
        }
    }
}
