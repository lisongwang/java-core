package com.lisong.learn.core.interfaces.facade.impl;

import com.lisong.learn.core.interfaces.facade.Controller;

import static com.lisong.learn.core.util.Print.print;

public class CarController implements Controller {

    @Override
    public void start() {
        print("Car start");
    }

    @Override
    public void stop() {
        print("Car stop");
    }

    @Override
    public void move() {
        print("Car moving");
    }
}
