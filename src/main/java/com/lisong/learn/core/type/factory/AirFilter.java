package com.lisong.learn.core.type.factory;

public class AirFilter extends Filter {

    static class Factory implements com.lisong.learn.core.util.Factory<AirFilter> {
        @Override
        public AirFilter create() {
            return new AirFilter();
        }
    }
}
