package com.lisong.learn.core.interfaces;

public class Exercise17 {

    public static void main(String[] args) {

        //INT_1 is static
        System.out.print(FieldIntf.INT_1);

        //INT_2 is final
        //(new FieldIntfImpl()).INT_1 = 20;
    }
}

interface FieldIntf {

    int INT_1 = 10;
}

class FieldIntfImpl implements FieldIntf {}