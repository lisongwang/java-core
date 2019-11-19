package com.lisong.learn.core.exceptions;

import java.util.logging.Logger;

/**
 * Combine exercise 6, exercise 7.
 */
public class Exercise7 {

    public static void main(String[] args) {

        try {
            throw new Exception6();
        }catch(Exception6 e) {
            e.printStackTrace();
        }

        try {
            throw new Exception7("warning");
        }catch(Exception7 e) {
            e.printStackTrace();
        }
    }
}

class Exception6 extends Exception {

    private static final Logger log = Logger.getLogger("Exception6");

    Exception6() {
        log.severe("Exception6 log by myself.");
    }
}

class Exception7 extends Exception {

    private static final Logger log = Logger.getLogger("Exception7");

    Exception7(String msg) {
        super(msg);
        log.warning("Exception7 log by myself as " + msg);
    }
}
