package com.lisong.learn.core.generics.coffee;

public class Breve extends Coffee {
    static class Factory implements com.lisong.learn.core.util.Factory<Breve> {
        @Override
        public Breve create() {
            return new Breve();
        }
    }
}
