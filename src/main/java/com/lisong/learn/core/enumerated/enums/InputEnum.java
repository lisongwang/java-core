package com.lisong.learn.core.enumerated.enums;

import java.util.Random;

public enum InputEnum implements Input {

    NICKEL(5), DIME(10), QUARTER(25), DOLLAR(100),
    //TOOTHPASTE(200), CHIPS(75), SODA(100), SOAP(50),
    ABORT_TRANSACTION {
        @Override
        public int amount() {
            throw new RuntimeException("ABORT.amount()");
        }
    },
    STOP {
        @Override
        public int amount() {
            throw new RuntimeException("SHUT_DOWN.amount()");
        }
    };

    private int amount;
    InputEnum(int amount) {
        this.amount = amount;
    }
    InputEnum() {}

    public int amount() { return amount; }

    private static Random rand = new Random(199);
    public static Input randomSelect() {
        return values()[rand.nextInt(values().length - 1)];
    }
}