package com.lisong.learn.core.type.pet;

public class Mutt extends Dog {
    Mutt(String name) {
        super(name);
    }

    Mutt() {
        super();
    }

    static class Factory implements com.lisong.learn.core.util.Factory<Mutt> {
        @Override
        public Mutt create() {
            return new Mutt();
        }
    }
}
