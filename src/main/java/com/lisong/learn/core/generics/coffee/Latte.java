package com.lisong.learn.core.generics.coffee;

public class Latte extends Coffee {
    static class Factory implements com.lisong.learn.core.util.Factory<Latte> {
        @Override
        public Latte create() {
            return new Latte();
        }
    }
}
