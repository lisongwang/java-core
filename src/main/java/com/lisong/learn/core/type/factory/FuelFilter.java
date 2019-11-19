package com.lisong.learn.core.type.factory;

public class FuelFilter extends Filter {

    static class Factory implements com.lisong.learn.core.util.Factory<FuelFilter> {
        @Override
        public FuelFilter create() {
            return new FuelFilter();
        }
    }
}
