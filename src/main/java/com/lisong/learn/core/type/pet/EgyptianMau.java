package com.lisong.learn.core.type.pet;

public class EgyptianMau extends Cat {
    EgyptianMau(String name) {
        super(name);
    }
    EgyptianMau() {
        super();
    }
    static class Factory implements com.lisong.learn.core.util.Factory<EgyptianMau> {
        @Override
        public EgyptianMau create() {
            return new EgyptianMau();
        }
    }
}
