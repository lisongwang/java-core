package com.lisong.learn.core.operators;

import static com.lisong.learn.core.util.Print.print;

/**
 * Combine exercise 5 and exercise 6 to show == and equals.
 */
public class Exercise6 {

    public static void main(String[] args) {

        Dog dog1 = new Dog("spot", "Ruff!");
        Dog dog2 = new Dog("scruffy", "Wurf!");

        dog1.printDog();
        dog2.printDog();

        Dog dog3 = dog1;

        print(dog1 == dog2);
        print(dog1 == dog3);
        print(dog1.equals(dog3));

    }
}

class Dog {

    String name;
    String says;

    Dog(String name, String says) {

        this.name = name;
        this.says = says;
    }

    void printDog() {

        print("Name:" + this.name + "  Says:" + this.says);
    }
}
