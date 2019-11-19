package com.lisong.learn.core.type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.lisong.learn.core.util.Print.print;

public class Exercise26 {

    public static void main(String[] args) {

        List<Instrument> orchestra = new ArrayList<>();
        Collections.addAll(orchestra,
                new Percussion(),
                new Stringed(),
                new Electronic(),
                new Wind());
        for(Instrument ins : orchestra) {
            ins.prepareInstrument();
        }
    }
}

abstract class Instrument {

    abstract void prepareInstrument();
    void commonClear() { print("Clear the instrument"); }
}

class Percussion extends Instrument {
    @Override
    void prepareInstrument() {
        commonClear();
    }
}
class Stringed extends Instrument {
    @Override
    void prepareInstrument() {
        commonClear();
    }
}
class Electronic extends Instrument {
    @Override
    void prepareInstrument() {
        commonClear();
    }
}
class Wind extends Instrument {
    @Override
    void prepareInstrument() {
        clearSpitValve();
    }

    private void clearSpitValve() {
        print("Clear spit valve");
    }
}


