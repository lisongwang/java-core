package com.lisong.learn.core.exceptions;

public class Exercise8 {

    void t() throws Exception4 {
        throw new Exception4("from t()");
    }

    public static void main(String[] args) {

        Exercise8 exe8 = new Exercise8();
        try {
            exe8.t();
        }catch(Exception4 e) {
            e.displayMsg();
            e.printStackTrace();
        }
    }
}


