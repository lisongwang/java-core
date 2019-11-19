package com.lisong.learn.core.innerclasses;

import static com.lisong.learn.core.util.Print.print;

public class Exercise14 {

    static void u(Monster b) {
        b.menace();
    }

    static void v(DangerousMonster d) {
        d.destroy();
    }

    static void w(Lethal l) {
        l.kill();
    }

    static void x(Vampire v) {
        v.drinkBlood();
    }

    public static void main(String[] args) {

        DangerousMonster dragonZilla = new DangerousMonster() {

            @Override
            public void menace() {
                print("Dragon menace.");
            }

            @Override
            public void destroy() {
                print("Dragon destroy");
            }
        };

        Vampire badVampire = new Vampire() {
            @Override
            public void menace() {
                print("BadVampire menace.");
            }

            @Override
            public void destroy() {
                print("BadVampire destroy.");
            }

            @Override
            public void kill() {
                print("BadVampire kill.");
            }

            @Override
            public void drinkBlood() {
                print("BadVampire drinkBlood.");
            }
        };

        u(dragonZilla);
        v(dragonZilla);
        u(badVampire);
        v(badVampire);
        w(badVampire);
        x(badVampire);
    }
}

interface Monster {
    void menace();
}

interface DangerousMonster extends Monster {
    void destroy();
}

interface Lethal {
    void kill();
}

interface Vampire extends DangerousMonster, Lethal {
    void drinkBlood();
}