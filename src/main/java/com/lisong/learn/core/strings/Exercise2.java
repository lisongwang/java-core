package com.lisong.learn.core.strings;

import java.util.ArrayList;
import java.util.List;

import static com.lisong.learn.core.util.Print.print;

public class Exercise2 {

    public static void main(String[] args) {

        List<InfiniteRecursion> l = new ArrayList<>();
        for(int i = 0; i < 10; i++)
            l.add(new InfiniteRecursion());
        print(l);
    }
}

class InfiniteRecursion {

    @Override
    public String toString() {
        return "InfiniteRecursion address is " + super.toString() + "\n";
    }
}
