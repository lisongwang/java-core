package com.lisong.learn.core.polymorphism;

import com.lisong.learn.core.polymorphism.food.FastFood;
import com.lisong.learn.core.polymorphism.food.Sandwich;

public class Exercise11 {

    public static void main(String[] args) {

        FastFood fd = new Sandwich();
        fd.quickCook();
    }
}
