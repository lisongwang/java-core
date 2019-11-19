package com.lisong.learn.core.enumerated;

import com.lisong.learn.core.enumerated.enums.Signal;

import static com.lisong.learn.core.enumerated.enums.Signal.*;
import static com.lisong.learn.core.util.Print.print;

public class Exercise1 {
    private Signal light = RED;
    private void change() {
        switch(light) {
            case RED: light = YELLOW; return;
            case YELLOW: light = GREEN; return;
            case GREEN: light = RED;
        }
    }

    @Override
    public String toString() {
        return "The light is: " + light;
    }

    public static void main(String[] args) {
        Exercise1 exe1 = new Exercise1();
        for(int i = 0; i < 7; i++) {
            print(exe1);
            exe1.change();
        }
    }
}