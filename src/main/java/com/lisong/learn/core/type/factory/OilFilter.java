package com.lisong.learn.core.type.factory;

public class OilFilter extends Filter {

    static class Factory implements com.lisong.learn.core.util.Factory<OilFilter> {
        @Override
        public OilFilter create() {
            return new OilFilter();
        }
    }
}
