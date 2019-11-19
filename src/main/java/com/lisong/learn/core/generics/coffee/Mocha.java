package com.lisong.learn.core.generics.coffee;

public class Mocha extends Coffee {
    static class Factory implements com.lisong.learn.core.util.Factory<Mocha> {
        @Override
        public Mocha create() {
            return new Mocha();
        }
    }
}
