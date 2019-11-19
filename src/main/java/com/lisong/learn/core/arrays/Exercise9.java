package com.lisong.learn.core.arrays;

import java.util.ArrayList;
import java.util.List;

import static com.lisong.learn.core.util.Print.print;

public class Exercise9 {

    public static void main(String[] args) {

        //Peel<Banana>[] peels = new Peel<Banana>[10];
        List<Peel<Banana>> peels = new ArrayList<>();
        for(int i = 0; i < 10; i++)
            peels.add(new Peel<>(new Banana()));
        print(peels);
        print(peels.getClass() + " " + peels.get(0).getClass());
    }
}

class Banana {
    private static long count = 0;
    private final long id = count++;

    @Override
    public String toString() {
        return "Banana " + id;
    }
}

class Peel<T> {
    private T t;
    Peel(T t) {
        this.t = t;
    }

    @Override
    public String toString() {
        return "Peel " + t;
    }
}