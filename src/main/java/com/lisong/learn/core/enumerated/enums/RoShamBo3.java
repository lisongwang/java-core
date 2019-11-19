package com.lisong.learn.core.enumerated.enums;

import com.lisong.learn.core.enumerated.util.RoShamBo;

import static com.lisong.learn.core.enumerated.enums.Outcome.*;

public enum RoShamBo3 implements Competitor<RoShamBo3> {

    PAPER,
    SCISSORS,
    ROCK;

    private static Outcome[][] table = {{DRAW,LOSE,WIN},{WIN,DRAW,LOSE},{DRAW,WIN,LOSE}};

    @Override
    public Outcome compete(RoShamBo3 roShamBo3) {
        return table[ordinal()][roShamBo3.ordinal()];
    }

    public static void main(String[] args) {
        RoShamBo.play(RoShamBo2.class, 20);
    }
}