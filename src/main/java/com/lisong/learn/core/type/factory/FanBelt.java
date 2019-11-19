package com.lisong.learn.core.type.factory;

public class FanBelt extends Belt {

    static class Factory implements com.lisong.learn.core.util.Factory<FanBelt> {
        @Override
        public FanBelt create() {
            return new FanBelt();
        }
    }
}
