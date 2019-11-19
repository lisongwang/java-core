package com.lisong.learn.core.containers;

import com.lisong.learn.core.containers.util.MySortedSet;
import com.lisong.learn.core.util.Generators;
import com.lisong.learn.core.util.RandomGenerator;

import java.util.Collections;

import static com.lisong.learn.core.util.Print.print;

public class Exercise10 {

    public static void main(String[] args) {

        MySortedSet<String> ss = new MySortedSet<>(String.CASE_INSENSITIVE_ORDER);
        Generators.fill(ss, new RandomGenerator.String(5), 15);
        print(ss);

        MySortedSet<Integer> ss1 = new MySortedSet<>(Collections.reverseOrder());
        ss1.add(3);
        ss1.add(9);
        ss1.add(90);
        ss1.add(97);
        ss1.add(2);
        ss1.add(31);
        ss1.add(76);
        ss1.add(123);
        ss1.add(16);
        print(ss1);
    }
}