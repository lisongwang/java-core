package com.lisong.learn.core.enumerated.enums;

public enum Signal {
    RED("RED(you can't go)"), YELLOW("YELLOW(warn of coming RED)"), GREEN("GREEN(please go)");

    private String s;
    Signal(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return s;
    }

    public static void main(String[] args) {
        for(Signal s : Signal.values())
            System.out.println(s);

    }
}