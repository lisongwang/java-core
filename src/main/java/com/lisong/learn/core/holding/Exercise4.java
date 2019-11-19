package com.lisong.learn.core.holding;

import java.util.*;

import static com.lisong.learn.core.util.Print.print;

public class Exercise4 {

    static String[] fillArray(String[] nameArray, Generator gen) {
        for(int i = 0; i < nameArray.length; i++) {
            nameArray[i] = gen.next();
        }
        return nameArray;
    }

    static Collection<String> fillCollection(Collection<String> collection, Generator gen, int size) {
        while(--size>=0)
            collection.add(gen.next());
        return collection;
    }

    public static void main(String[] args) {
        Generator gen = new Generator();
        print(Arrays.toString(fillArray(new String[5], gen)));
        print(fillCollection(new ArrayList<>(), gen, 5));
        print(fillCollection(new LinkedList<>(), gen, 5));
        print(fillCollection(new HashSet<>(), gen, 5));
        print(fillCollection(new TreeSet<>(), gen, 5));
        print(fillCollection(new LinkedHashSet<>(), gen, 5));
    }
}

class Generator {

    private int index = 0;

    private String getName(int key) {
        switch(key) {
            default:
            case 0: return "Harry";
            case 1: return "Polly";
            case 2: return "John";
            case 3: return "July";
            case 4: return "Lucy";
            case 5: return "Lily";
            case 6: return "Steven";
            case 7: return "Bob";
        }
    }

    String next() {
        return getName(index++%8);
    }
}
