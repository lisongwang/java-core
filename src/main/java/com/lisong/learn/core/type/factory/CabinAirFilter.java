package com.lisong.learn.core.type.factory;

public class CabinAirFilter extends Filter {

    static class Factory implements com.lisong.learn.core.util.Factory<CabinAirFilter> {
        @Override
        public CabinAirFilter create() {
            return new CabinAirFilter();
        }
    }
}
