package com.lisong.learn.core.interfaces;

import com.lisong.learn.core.polymorphism.vehicle.Bicycle;
import com.lisong.learn.core.polymorphism.vehicle.Cycle;
import com.lisong.learn.core.polymorphism.vehicle.Tricycle;
import com.lisong.learn.core.polymorphism.vehicle.Unicycle;

public class Exercise18 {

    static void runCycle(CycleFactory cf) {

        Cycle cy = cf.getCycle();
        cy.ride();
    }

    public static void main(String[] args) {
        runCycle(Unicycle.factory);
        runCycle(Bicycle.factory);
        runCycle(Tricycle.factory);
    }
}
