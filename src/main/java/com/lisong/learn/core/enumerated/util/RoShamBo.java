package com.lisong.learn.core.enumerated.util;

import com.lisong.learn.core.enumerated.enums.Competitor;

import static com.lisong.learn.core.util.Print.print;

public class RoShamBo {

    static <T extends Competitor<T>> void match(T t1, T t2) {
         print(t1 + " vs " + t2 + ": " + t1.compete(t2));
    }

    public static <T extends Enum<T> & Competitor<T>> void play(Class<T> clazz, int size) {
        for(int i = 0; i < size; i++) {
            match(Enums.random(clazz), Enums.random(clazz));
        }
    }
}