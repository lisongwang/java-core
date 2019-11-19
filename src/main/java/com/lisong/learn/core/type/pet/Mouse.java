package com.lisong.learn.core.type.pet;

public class Mouse extends Rodent {
    Mouse(String name) {
        super(name);
    }

    Mouse() {
        super();
    }
    static class Factory implements com.lisong.learn.core.util.Factory<Mouse> {
        @Override
        public Mouse create() {
            return new Mouse();
        }
    }
}
