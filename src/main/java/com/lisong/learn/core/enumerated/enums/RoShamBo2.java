package com.lisong.learn.core.enumerated.enums;

import com.lisong.learn.core.enumerated.util.RoShamBo;

import static com.lisong.learn.core.enumerated.enums.Outcome.*;

public enum RoShamBo2 implements Competitor<RoShamBo2> {

    PAPER(DRAW, LOSE, WIN),
    SCISSORS(WIN, DRAW, LOSE),
    ROCK(LOSE, WIN, DRAW);

    private Outcome vPaper, vScissors, vRock;
    RoShamBo2(Outcome vPaper, Outcome vScissors, Outcome vRock) {
        this.vPaper = vPaper;
        this.vScissors = vScissors;
        this.vRock = vRock;
    }

    @Override
    public Outcome compete(RoShamBo2 roShamBo2) {
        switch(roShamBo2) {
            default:
            case PAPER: return vPaper;
            case SCISSORS: return vScissors;
            case ROCK: return vRock;
        }
    }

    public static void main(String[] args) {
        RoShamBo.play(RoShamBo2.class, 20);
    }
}