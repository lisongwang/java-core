package com.lisong.learn.core.innerclasses;

import com.lisong.learn.core.innerclasses.facade.Event;
import com.lisong.learn.core.innerclasses.facade.Impl.GreenhouseController;

/**
 * Combine exercise 24, exercise 25.
 */
public class Exercise25 {

    public static void main(String[] args) {

        GreenhouseController controller = new GreenhouseController();
        controller.addEvent(controller.new Bell(1000));
        Event[] events = new Event[] {
                controller.new ThermostatDay(0),
                controller.new LightOn (100),
                controller.new WaterOn(200),
                controller.new WaterMistOn(300),
                controller.new FansOn(400),
                controller.new FansOff(1200),
                controller.new WaterMistOff(1300),
                controller.new WaterOff(1400),
                controller.new LightOff(1500),
                controller.new ThermostatNight(1600),
        };
        controller.addEvent(controller.new Restart(2000, events));
        controller.addEvent(new GreenhouseController.Terminate(3000));
        controller.run();
    }
}