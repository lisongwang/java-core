package com.lisong.learn.core.initialization;

import static com.lisong.learn.core.util.Print.print;

/**
 * Combine exercise 5, exercise 6, exercise 7.
 */
public class Exercise7 {

    public static void main(String[] args) {

        Dog dog = new Dog();

        dog.bark('a');
        dog.bark((byte)10);
        dog.bark((short)100);
        dog.bark(1000);
        dog.bark(10000L);
        dog.bark(100000.0f);
        dog.bark(1000000.0d);

        dog.bark(9, 9.9);
        dog.bark(9.9, 9);
    }
}

class Dog {

    void bark (char i) {
        print("barking in char " + i);
    }
    void bark (byte i) {
        print("barking in byte " + i);
    }
    void bark (short i) {
        print("barking in short " + i);
    }
    void bark (int i) {
        print("barking in int " + i);
    }
    void bark (long i) {
        print("barking in long " + i);
    }
    void bark (float i) {
        print("barking in float " + i);
    }
    void bark (double i) {
        print("barking in double " + i);
    }

    void bark (int i, double d) {
        print("barking in int " + i + " and double " + d);
    }
    void bark (double d, int i) {
        print("barking in double " + d + " and int " + i);
    }
}
