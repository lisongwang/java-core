package com.lisong.learn.core.holding;

import java.util.*;

import static com.lisong.learn.core.util.Print.print;

public class Exercise6 {

    public static void main(String[] args) {

        Generator gen = new Generator();
        List<String> strings = new LinkedList<>();
        for(int i = 0; i < 8; i++)
            strings.add(gen.next());

        print("1: " + strings);
        String s = "Jessie";
        strings.add(s);
        print("2: " + strings);
        print("3: " + strings.contains(s));
        strings.remove(s);
        String s1 = strings.get(2);
        print("4: " + s1 + " " + strings.indexOf(s1));
        String s2 = "Larry";
        print("5: " + strings.indexOf(s2));
        print("6: " + strings.remove(s2));
        print("7: " + strings.remove(s1)); // remove by object
        print("8: " + strings);
        strings.add(3, gen.next()); //insert at index
        print("9: " + strings);
        List<String> sub = strings.subList(1, 4);
        print("sublist: " + sub);
        print("A0: " + strings.containsAll(sub));
        Collections.sort(sub);
        print("A1: " + strings.containsAll(sub));
        Collections.shuffle(sub);
        print("shuffled sublist: " + sub);
        print("A2: " + strings.containsAll(sub));
        List<String> copy = new LinkedList<>(strings);
        sub = Arrays.asList(strings.get(1), strings.get(4));
        print("sub: " + sub);
        copy.retainAll(sub);
        print("A3: " + copy);
        copy = new LinkedList<>(strings);
        copy.remove(2);
        print("A4: " + copy);
        copy.removeAll(sub);
        print("A5: " + copy);
        copy.set(1, gen.next());
        print("A6: " + copy);
        copy.addAll(2, sub);
        print("A7: " + copy);
        print("A8: " + strings.isEmpty());
        strings.clear();
        print("A9: " + strings);
        print("B0: " + strings.isEmpty());
        strings.addAll(Arrays.asList(new String[]{"HH", "JJ", "KK", "MM"}));
        print("B1: " + strings);
        Object[] o = strings.toArray();
        print("B2: " + o[3]);
        String[] strings1 = strings.toArray(new String[0]);
        print("B3: " + strings1[1]);
    }
}
