package com.lisong.learn.core.polymorphism;

import com.lisong.learn.core.polymorphism.vehicle.Bicycle;
import com.lisong.learn.core.polymorphism.vehicle.Cycle;
import com.lisong.learn.core.polymorphism.vehicle.Tricycle;
import com.lisong.learn.core.polymorphism.vehicle.Unicycle;

/**
 * Combine exercise 1, exercise 5.
 */
public class Exercise1 {

    static void ride(Cycle c) {
        c.ride();
        System.out.println(" with " + c.wheels() + " wheels");
    }

    public static void main(String[] args) {

        Unicycle uc = (Unicycle)Unicycle.factory.getCycle();
        Bicycle bc = (Bicycle)Bicycle.factory.getCycle();
        Tricycle tc = (Tricycle)Tricycle.factory.getCycle();
        ride(uc);
        ride(bc);
        ride(tc);
    }
}

