package com.lisong.learn.core.containers;

import com.lisong.learn.core.containers.util.Countries;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

import static com.lisong.learn.core.util.Print.print;

public class Exercise3 {

    public static void main(String[] args) {

        Set<String> set1 = new HashSet<>(Countries.names());
        Set<String> set2 = new LinkedHashSet<>(Countries.names());
        Set<String> set3 = new TreeSet<>(Countries.names());
        print("set1: " + set1);
        print("set2: " + set2);
        print("set3: " + set3);
        for(int i = 0; i < 10; i++) {
            set1.addAll(Countries.names());
            set2.addAll(Countries.names());
            set3.addAll(Countries.names());
        }
        print("After 10 times add the same collection");
        print("set1: " + set1);
        print("set2: " + set2);
        print("set3: " + set3);
    }
}
