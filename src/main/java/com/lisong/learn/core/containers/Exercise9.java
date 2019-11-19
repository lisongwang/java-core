package com.lisong.learn.core.containers;

import com.lisong.learn.core.util.Generators;
import com.lisong.learn.core.util.RandomGenerator;

import java.util.TreeSet;

import static com.lisong.learn.core.util.Print.print;

public class Exercise9 {

    public static void main(String[] args) {

        TreeSet<String> ss = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        Generators.fill(ss, new RandomGenerator.String(5), 10);
        print(ss);
    }
}
