package com.lisong.learn.core.enumerated;

import com.lisong.learn.core.enumerated.enums.CartoonCharacter;

import static com.lisong.learn.core.util.Print.print;

public class Exercise2 {

    public static void main(String[] args) {
        for(int i = 0; i < 10; i++)
            print(CartoonCharacter.next());
    }
}