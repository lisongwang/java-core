package com.lisong.learn.core.type.pet;

public class Pug extends Dog {
    Pug(String name) {
        super(name);
    }

    Pug() {
        super();
    }

    static class Factory implements com.lisong.learn.core.util.Factory<Pug> {
        @Override
        public Pug create() {
            return new Pug();
        }
    }
}
