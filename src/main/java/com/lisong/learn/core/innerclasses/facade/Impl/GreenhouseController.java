package com.lisong.learn.core.innerclasses.facade.Impl;

import com.lisong.learn.core.innerclasses.facade.Controller;
import com.lisong.learn.core.innerclasses.facade.Event;

public class GreenhouseController extends Controller {

    private boolean light = false;

    public class LightOn extends Event {
        public LightOn(long delayTime) {
            super(delayTime);
        }

        @Override
        public String toString() {
            return "Light is on";
        }

        @Override
        public void action() {
            light = true;
        }
    }

    public class LightOff extends Event {
        public LightOff(long delayTime) {
            super(delayTime);
        }

        @Override
        public String toString() {
            return "Light is off";
        }

        @Override
        public void action() {
            light = false;
        }
    }

    private boolean water = false;

    public class WaterOn extends Event {
        public WaterOn(long delayTime) {
            super(delayTime);
        }

        @Override
        public String toString() {
            return "Greenhouse water is on";
        }

        @Override
        public void action() {
            water = true;
        }
    }

    public class WaterOff extends Event {
        public WaterOff(long delayTime) {
            super(delayTime);
        }

        @Override
        public String toString() {
            return "Greenhouse water is off";
        }

        @Override
        public void action() {
            water = false;
        }
    }

    private boolean waterMist = false;

    public class WaterMistOn extends Event {
        public WaterMistOn(long delayTime) {
            super(delayTime);
        }

        @Override
        public String toString() {
            return "Water mist generators on";
        }

        @Override
        public void action() {
            waterMist = true;
        }
    }

    public class WaterMistOff extends Event {
        public WaterMistOff(long delayTime) {
            super(delayTime);
        }

        @Override
        public String toString() {
            return "Water mist generators off";
        }

        @Override
        public void action() {
            waterMist = false;
        }
    }

    private String thermostat = "Day";

    public class ThermostatDay extends Event {
        public ThermostatDay(long delayTime) {
            super(delayTime);
        }

        @Override
        public String toString() {
            return "Thermostat on day setting";
        }

        @Override
        public void action() {
            thermostat = "Day";
        }
    }

    public class ThermostatNight extends Event {
        public ThermostatNight(long delayTime) {
            super(delayTime);
        }

        @Override
        public String toString() {
            return "Thermostat on night setting";
        }

        @Override
        public void action() {
            thermostat = "Night";
        }
    }

    private boolean fans = false;

    public class FansOn extends Event {
        public FansOn(long delayTime) {
            super(delayTime);
        }

        @Override
        public String toString() {
            return "Greenhouse fans is on";
        }

        @Override
        public void action() {
            fans = true;
        }
    }

    public class FansOff extends Event {
        public FansOff(long delayTime) {
            super(delayTime);
        }

        @Override
        public String toString() {
            return "Greenhouse fans is off";
        }

        @Override
        public void action() {
            fans = false;
        }
    }

    public class Bell extends Event {
        public Bell(long delayTime) {
            super(delayTime);
        }

        @Override
        public String toString() {
            return "Bing!";
        }

        @Override
        public void action() {
            addEvent(new Bell(delayTime));
        }
    }

    public class Restart extends Event {
        private Event[] events;
        public Restart(long delayTime, Event[] eventlist) {
            super(delayTime);
            events =  eventlist;
            for(Event e : events) {
                addEvent(e);
            }
        }

        @Override
        public String toString() {
            return "Restarting system!";
        }

        @Override
        public void action() {
            for(Event e : events) {
                e.start();
                addEvent(e);
            }
            start();
            addEvent(this);
        }
    }

    public static class Terminate extends Event {
        public Terminate(long delayTime) {
            super(delayTime);
        }

        @Override
        public String toString() {
            return "Terminating";
        }

        @Override
        public void action() {
            System.exit(0);
        }
    }
}