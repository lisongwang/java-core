package com.lisong.learn.core.exceptions;

import static com.lisong.learn.core.util.Print.print;

public class Exercise20 {

    public static void main(String[] args) {

        try {
            StormyInning storm = new StormyInning();
            storm.atBat();
            storm.call();
            storm.event();
        }catch(ThrownFromGame e) {
            print("Caught ThrownFromGame here");
        }catch(UmpireArgument e) {
            print("Caught UmpireArgument here");
        }catch(RainedOut e) {
            print("Caught RainedOut here");
        }catch(PopFoul e) {
            print("Caught PopFoul here");
        }catch(BaseballException e) {
            print("Caught BaseballException here");
        }

        try {
            Inning inn = new StormyInning("s");
            inn.call();
            inn.atBat();
        }catch(Foul e) {
            print("Caught Foul here");
        }catch(UmpireArgument e) {
            print("Caught UmpireArgument here");
        }catch(BaseballException e) {
            print("Caught BaseballException here");
        }catch(BodyException e) {
            print("Caught BodyException here");
        }
    }
}

class BaseballException extends Exception {}
class BodyException extends Exception {}
class Foul extends BaseballException {}
class UmpireArgument extends BaseballException {}
class ThrownFromGame extends UmpireArgument {}

abstract class Inning {

    Inning() throws BaseballException {}

    void event() throws BaseballException {}

    abstract void atBat() throws BodyException, Foul, UmpireArgument;
    void walk() {}
    void call() throws UmpireArgument {}
}

class StormException extends Exception {}
class RainedOut extends StormException {}
class PopFoul extends Foul {}

interface Storm {
    void event() throws RainedOut, UmpireArgument;
    void rainHard() throws RainedOut;
}

class StormyInning extends Inning implements Storm {

    StormyInning() throws UmpireArgument, RainedOut, BaseballException {
        super();
    }

    StormyInning(String s) throws Foul, BaseballException {}

    @Override
    void atBat() throws PopFoul, ThrownFromGame {
        throw new ThrownFromGame();
    }

    @Override
    public void event() throws ThrownFromGame {}

    @Override
    public void rainHard() throws RainedOut {}

    @Override
    void call() throws UmpireArgument {
        throw new UmpireArgument();
    }
}