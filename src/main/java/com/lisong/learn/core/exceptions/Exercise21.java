package com.lisong.learn.core.exceptions;

import static com.lisong.learn.core.util.Print.print;

public class Exercise21 {

    public static void main(String[] args) {

        try {
            SubClass sub = new SubClass();
        }catch(BaseException e) {
            print("Caught BaseException in main");
        }
    }
}

class BaseException extends Exception {}

class BaseClass {
    BaseClass() throws BaseException { throw new BaseException(); }
}

class SubClass extends BaseClass {
    SubClass() throws BaseException {}
}