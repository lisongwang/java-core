package com.lisong.learn.core.exceptions;

public class Exercise2 {

    private static String text = null;

    public static void main(String[] args) {

        try {
            text.toCharArray();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
