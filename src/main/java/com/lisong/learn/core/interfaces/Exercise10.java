package com.lisong.learn.core.interfaces;

import com.lisong.learn.core.interfaces.facade.Controller;
import com.lisong.learn.core.interfaces.facade.impl.CarController;

/**
 * Combine exercise 5, exercise 6, exercise 7, exercise 8, exercise 9, exercise 10.
 */
public class Exercise10 {

    public static void main(String[] args) {
        Controller controller = new CarController();
        controller.start();
        controller.move();
        controller.stop();
    }
}
