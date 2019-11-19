package com.lisong.learn.core.exceptions;

import static com.lisong.learn.core.util.Print.print;

public class Exercise4 {

    public static void main(String[] args) {

        try {
            throw new Exception4("I am a exception!");
        }catch (Exception4 e) {
            e.printStackTrace();
            e.displayMsg();
        }
    }
}

class Exception4 extends Exception {

    private String msg;

    Exception4(String msg) {
        super(msg);
        this.msg = msg;
    }

    void displayMsg() {
        print(msg);
    }
}
