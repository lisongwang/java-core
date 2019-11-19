package com.lisong.learn.core.type.pet;

public class Pet {

    private static long counter = 0;
    private final long id = ++counter;
    private String name;

    Pet(String name) { this.name = name; }
    Pet() { name = null; }
    public long id() { return id; }

    @Override
    public String toString() {
        return name == null ? this.getClass().getSimpleName() : name;
    }
}
