package com.lisong.learn.core.enumerated;

import com.lisong.learn.core.enumerated.enums.Cycle;

import java.util.EnumSet;

import static com.lisong.learn.core.enumerated.enums.Cycle.*;
import static com.lisong.learn.core.util.Print.print;

/**
 * Show the use of EnumSet as well as constant-specific method.
 */
public class Exercise7 {

    private EnumSet<Cycle> cycles = EnumSet.of(BASIC, RINSE);
    private void washCar() {
        for(Cycle c : cycles)
            c.action();
    }
    private boolean add(Cycle c) { return cycles.add(c); }
    @Override
    public String toString() {
        return cycles.toString();
    }
    private EnumSet<Cycle> another;
    private void copy() {
        another = cycles.clone();
    }

    public static void main(String[] args) {
        Exercise7 exe7 = new Exercise7();
        print(exe7);
        exe7.washCar();
        exe7.add(RINSE);
        exe7.add(BLOWDRY);
        exe7.add(WHEELWASH);
        exe7.add(PREWASH);
        print(exe7);
        exe7.washCar();
        exe7.copy();
        print(exe7.another);
    }
}