package com.lisong.learn.core.reusing;

import static com.lisong.learn.core.util.Print.print;

public class Exercise6 {

    public static void main(String... args) {
        Chess x = new Chess();
    }
}

class Game {

    Game(int i) { print("Game constructor"); }
}

class BoardGame extends Game {

    BoardGame(int i) {
        super(i);
        print("BoardGame constructor"); }
}

class Chess extends BoardGame {

    Chess() {
        super(11);
        print("Chess constructor");
    }
}