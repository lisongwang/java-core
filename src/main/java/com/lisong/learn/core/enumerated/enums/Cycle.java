package com.lisong.learn.core.enumerated.enums;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import static com.lisong.learn.core.util.Print.print;

public enum Cycle {

    UNDERBODY {
        @Override
        public void action() { print("Spraying the underbody"); }
    },
    WHEELWASH {
        @Override
        public void action() {
            print("Washing the wheels");
        }
    },
    PREWASH {
        @Override
        public void action() {
            print("Loosening the dirt");
        }
    },
    BASIC {
        @Override
        public void action() {
            print("The basic wash");
        }
    },
    HOTWAX {
        @Override
        public void action() {
            print("Applying hot wax");
        }
    },
    RINSE {
        @Override
        public void action() {
            print("Rinsing");
        }
    },
    BLOWDRY {
        @Override
        public void action() {
            print("Blowing dry");
        }
    };
    public abstract void action();

    public static void main(String[] args) {
        print(BLOWDRY.getClass());
        Constructor[] con = BLOWDRY.getClass().getDeclaredConstructors();
        print(Arrays.toString(con));
        try {
            //Cannot reflectively create enum objects
            con[0].newInstance("BLOWDRY",7);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        /*try {
            //no default constructor within BLOWDRY.class
            BLOWDRY.getClass().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }*/
    }
}