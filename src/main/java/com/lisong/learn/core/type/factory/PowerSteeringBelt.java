package com.lisong.learn.core.type.factory;

public class PowerSteeringBelt extends Belt {

    static class Factory implements com.lisong.learn.core.util.Factory<PowerSteeringBelt> {
        @Override
        public PowerSteeringBelt create() {
            return new PowerSteeringBelt();
        }
    }
}
