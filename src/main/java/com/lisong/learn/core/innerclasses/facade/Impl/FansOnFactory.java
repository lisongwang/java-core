package com.lisong.learn.core.innerclasses.facade.Impl;

import com.lisong.learn.core.innerclasses.facade.Event;
import com.lisong.learn.core.innerclasses.facade.EventFactory;

public class FansOnFactory implements EventFactory<GreenhouseController> {
    @Override
    public Event produce(GreenhouseController greenhouseController, Long delay) {
        return greenhouseController.new FansOn(delay);
    }
}