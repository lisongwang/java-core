package com.lisong.learn.core.polymorphism;

import com.lisong.learn.core.polymorphism.vehicle.Bicycle;
import com.lisong.learn.core.polymorphism.vehicle.Cycle;
import com.lisong.learn.core.polymorphism.vehicle.Tricycle;
import com.lisong.learn.core.polymorphism.vehicle.Unicycle;

public class Exercise17 {

    public static void main(String[] args) {

        Cycle[] cys = new Cycle[] {
                Bicycle.factory.getCycle(),
                Unicycle.factory.getCycle(),
                Tricycle.factory.getCycle(),
        };

        //cys[0].balance();
        ((Bicycle)cys[0]).balance();
        ((Unicycle)cys[1]).balance();
        //((Tricycle)cys[2]).balance();
        //((Bicycle)cys[2]).balance();
    }
}
