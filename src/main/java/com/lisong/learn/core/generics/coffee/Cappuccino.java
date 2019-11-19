package com.lisong.learn.core.generics.coffee;

public class Cappuccino extends Coffee {
    static class Factory implements com.lisong.learn.core.util.Factory<Cappuccino> {
        @Override
        public Cappuccino create() {
            return new Cappuccino();
        }
    }
}
