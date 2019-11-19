package com.lisong.learn.core.type.factory;

public class GeneratorBelt extends Belt {

    static class Factory implements com.lisong.learn.core.util.Factory<GeneratorBelt> {
        @Override
        public GeneratorBelt create() {
            return new GeneratorBelt();
        }
    }
}
