package com.lisong.learn.core.generics.coffee;

public class Americano extends Coffee {
    static class Factory implements com.lisong.learn.core.util.Factory<Americano> {
        @Override
        public Americano create() {
            return new Americano();
        }
    }
}
