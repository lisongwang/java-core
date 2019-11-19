package com.lisong.learn.core.exceptions;

import com.lisong.learn.core.polymorphism.animal.Frog;

public class Exercise17 {

    public static void main(String[] args) {
        Frog frog = new Frog();
        try {
            return;
        }finally {
            frog.dispose();
        }
    }
}
