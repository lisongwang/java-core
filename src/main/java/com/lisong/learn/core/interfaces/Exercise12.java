package com.lisong.learn.core.interfaces;

import static com.lisong.learn.core.util.Print.print;

public class Exercise12 {

    static void t(CanFight cf) { cf.fight(); }
    static void u(CanSwim cs) { cs.swim(); }
    static void v(CanFly cy) { cy.fly(); }
    static void x(CanClimb cc) { cc.climb(); }
    static void w(ActionCharacter ac) { ac.fight(); }

    public static void main(String[] args) {

        Hero hero = new Hero();
        t(hero);
        u(hero);
        v(hero);
        x(hero);
        w(hero);
    }
}

class Hero extends ActionCharacter implements CanFight, CanSwim, CanFly, CanClimb {

    @Override
    public void fly() { print("I am flying"); }

    @Override
    public void swim() {
        print("I am swimming");
    }

    @Override
    public void climb() {
        print("I am climbing");
    }
}

interface CanFight {
    void fight();
}

interface CanSwim {
    void swim();
}

interface CanFly {
    void fly();
}

interface CanClimb {
    void climb();
}

class ActionCharacter {

    public void fight() {
        print("Who is fight?");
    }
}