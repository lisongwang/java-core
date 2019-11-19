package com.lisong.learn.core.interfaces;

import static com.lisong.learn.core.util.Print.print;

/**
 * Combine exercise 14, exercise 15.
 */
public class Exercise15 {

    static void e(Human hu) { hu.eat(); hu.sleep(); }
    static void h(Appearance ap) { ap.height(); ap.weight(); }
    static void c(Behavior be) { be.cry(); be.largh(); }
    static void b(Baby bb) { bb.age(); }

    public static void main(String[] args) {

        Girl girl = new Girl("姗姗");
        girl.say();
        e(girl);
        h(girl);
        c(girl);
        b(girl);
    }
}

class Girl extends MyBaby implements Baby {

    Girl(String name) {
        super(name);
    }

    @Override
    public void eat() {
        print(this.name() + "吃饭");
    }

    @Override
    public void sleep() {
        print(this.name() + "睡觉");
    }

    @Override
    public void height() {
        print(this.name() + "身高150厘米");
    }

    @Override
    public void weight() {
        print(this.name() + "体重35斤");
    }

    @Override
    public void cry() {
        print(this.name() + "哭");
    }

    @Override
    public void largh() {
        print(this.name() + "笑");
    }

    @Override
    public void age() {
        print(this.name() + "10岁");
    }

    @Override
    void say() {
        print("My name is " + this.name());
    }
}

interface Human {

    void eat();
    void sleep();
}

interface Appearance {

    void height();
    void weight();
}

interface Behavior {

    void cry();
    void largh();
}

interface Baby extends Human, Appearance, Behavior {
    void age();
}

abstract class MyBaby {

    private String name;
    MyBaby(String name) { this.name = name; }
    String name() { return this.name; }
    abstract void say();
}
