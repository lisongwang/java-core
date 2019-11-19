package com.lisong.learn.core.exceptions;

import com.lisong.learn.core.polymorphism.shape.CADSystem;

public class Exercise16 {

    public static void main(String[] args) {

        CADSystem cad = new CADSystem();
        try {
            cad.draw();
            return;
        }finally {
            cad.dispose();
        }
    }
}
