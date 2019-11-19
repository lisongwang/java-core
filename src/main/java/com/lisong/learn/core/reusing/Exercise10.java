package com.lisong.learn.core.reusing;

import static com.lisong.learn.core.util.Print.print;

/**
 * Combine exercise 9, exercise 10.
 */
public class Exercise10 {

    public static void main(String[] args) {

        Stem s = new Stem(2.73);
    }
}

class Stem extends Root {

    Stem(double d) {
        super((float)d);
        comp_s1 = new Component1((byte)2);
        comp_s2 = new Component2((short)22);
        comp_s3 = new Component3(222);
        print("Stem(double)");
    }

    void dispose() {
        print("Stem dispose");
        comp_s3.dispose();
        comp_s2.dispose();
        comp_s1.dispose();
        super.dispose();
    }

    Component1 comp_s1;
    Component2 comp_s2;
    Component3 comp_s3;
}

class Root {

    Root(float f) {
        comp1 = new Component1((byte)1);
        comp2 = new Component2((short)11);
        comp3 = new Component3(111);
        print("Root(float)");
    }

    void dispose() {
        print("Root dispose");
        comp3.dispose();
        comp2.dispose();
        comp1.dispose();
    }

    Component1 comp1;
    Component2 comp2;
    Component3 comp3;
}

class Component1 {

    Component1(byte b) { print("Component1(" + b + ")"); }
    void dispose() { print("Component1 dispose"); }
}

class Component2 {

    Component2(short s) { print("Component2(" + s + ")"); }
    void dispose() { print("Component2 dispose"); }
}

class Component3 {

    Component3(int i) { print("Component3(" + i + ")"); }
    void dispose() { print("Component3 dispose"); }
}