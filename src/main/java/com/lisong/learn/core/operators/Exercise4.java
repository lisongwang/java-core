package com.lisong.learn.core.operators;

import java.util.Random;

import static com.lisong.learn.core.util.Print.print;


/**
 * Calc the velocity by division.
 */
public class Exercise4 {

    static double calcVelocity(float distance, float time) {

        if (time == 0)
            return 0d;
        else
            return distance/time;
    }

    public static void main(String[] args) {

        Random rand = new Random(180);

        float distance = rand.nextFloat();
        float time = rand.nextFloat();
        print("Distance is " + distance);
        print("Time is " + time);
        double velocity = calcVelocity(distance, time);
        print("Velocity is " + velocity);
    }
}
