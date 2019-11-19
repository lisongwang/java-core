package com.lisong.learn.core.interfaces;

import java.util.Random;

public class Exercise19 {

    static void playGame(GameFactory gf, int times) {

        Game game = gf.getGame(times);
        game.play();
    }

    public static void main(String[] args) {

        playGame(CoinGame.factory, 7);
        System.out.println();
        playGame(DiceGame.factory, 5);
    }
}

interface Game {

    void play();
}

interface GameFactory {

    Game getGame(int times);
}

abstract class TossingGame implements Game {

    private Random rand = new Random(47);
    private boolean isReady = false;

    protected int times;
    protected int dimension;
    protected String[] results;

    TossingGame(int times, int dimension) {
        this.times = times;
        this.dimension = dimension;
        results = new String[dimension];
    }

    protected void ready() { this.isReady = true; }

    abstract void initResults();

    @Override
    public void play() {
        if(!isReady)
            initResults();

        while(times-- > 0) {
           System.out.print(results[rand.nextInt(dimension)] + " ");
        }

    }
}

class CoinGame extends TossingGame {

    private static final int DIE = 2;

    private CoinGame(int times) {
        super(times, DIE);
    }

    @Override
    void initResults() {
        if(results != null) {
            for(COIN coi : COIN.values()) {
                results[coi.ordinal()] = coi.name();
            }
            ready();
        }
    }

    static final GameFactory factory = new GameFactory() {
        @Override
        public Game getGame(int times) {
            return new CoinGame(times);
        }
    };
}

class DiceGame extends TossingGame {

    private static final int DIE = 6;

    private DiceGame(int times) {
        super(times, DIE);
    }

    @Override
    void initResults() {
        if(results != null) {
            for(Dice dic : Dice.values()) {
                results[dic.ordinal()] = dic.name();
            }
            ready();
        }
    }

    static final GameFactory factory = new GameFactory() {
        @Override
        public Game getGame(int times) {
            return new DiceGame(times);
        }
    };
}

enum Dice {
    ONE, TWO, THREE, FOUR, FIVE, SIX,
}

enum COIN {
    FRONT, BACK
}