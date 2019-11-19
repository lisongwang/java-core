package com.lisong.learn.core.reusing;

import static com.lisong.learn.core.util.Print.print;

/**
 * Show the composition and inheritance
 */
public class Exercise14 {

    public static void main(String[] args) {

        Car car = new Car();
        car.engine.start();
        car.engine.service();
        car.left.window.rollup();
        car.right.window.rolldown();
    }
}

class Engine {

    public void start() { print("Engine start"); }
    public void stop() { print("Engine stop"); }
    public void rev() { print("Engine rev"); }
    public void service() { print("Engine service"); }
}

class Wheel {

    public void inflate(int psi) { print("Wheel inflate at " + psi); }
}

class Window {

    public void rollup() { print("Window rollup"); }
    public void rolldown() { print("Window rolldown"); }
}

class Door {

    public Window window = new Window();
    public void open() { print("Door open"); }
    public void close() { print("Door close"); }
}

class Car {

    public Engine engine = new Engine();
    public Wheel[] wheels = new Wheel[4];
    public Door
    left = new Door(),
    right = new Door();

    public Car() {
        for (int i = 0; i < wheels.length; i++) {
            wheels[i] = new Wheel();
        }
    }
}