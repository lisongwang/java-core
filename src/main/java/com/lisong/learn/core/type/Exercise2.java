package com.lisong.learn.core.type;

import static com.lisong.learn.core.util.Print.print;

/**
 * Combine exercise 1, exercise 2.
 */
public class Exercise2 {

    static void printInfo(Class c) {

        print("Class name: " + c.getName() +
                " is interface? " + c.isInterface());
        print("Simple name: " + c.getSimpleName());
        print("Canonical name: " + c.getCanonicalName());
    }
    public static void main(String[] args) {

        Class c = null;
        try {
            c = Class.forName("com.lisong.learn.core.type.FancyToy");
        }catch(ClassNotFoundException e) {
            print("Class not found!");
        }
        printInfo(c);
        for(Class c1 : c.getInterfaces()) {
            printInfo(c1);
        }
        Class up = c.getSuperclass();
        Object obj = null;
        try {
            obj = up.newInstance();
        }catch(InstantiationException e) {
            print("Can't instantiate");
        }catch(IllegalAccessException e) {
            print("Cant access");
        }
        printInfo(obj.getClass());
    }
}

interface HasBatteries {}
interface Waterproof {}
interface Shoots {}
interface Eatable {}

class Toy {

    Toy() {}
    Toy(int i) {}
}

class FancyToy extends Toy implements HasBatteries, Waterproof, Shoots, Eatable {
    FancyToy(int i) {
        super(i);
    }
}
