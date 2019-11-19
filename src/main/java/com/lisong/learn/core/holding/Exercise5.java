package com.lisong.learn.core.holding;

import java.util.*;

import static com.lisong.learn.core.util.Print.print;

public class Exercise5 {

    public static void main(String[] args) {

        Random rand = new Random(55);
        List<Integer> ints = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            ints.add(rand.nextInt(100));
        }

        print("1: " + ints);
        Integer i = 1000;
        ints.add(i);
        print("2: " + ints);
        print("3: " + ints.contains(i));
        ints.remove(i);
        Integer j = ints.get(2);
        print("4: " + j + " " + ints.indexOf(j));
        Integer k = 1010;
        print("5: " + ints.indexOf(k));
        print("6: " + ints.remove(k));
        print("7: " + ints.remove(j)); // remove by object
        print("8: " + ints);
        ints.add(3, rand.nextInt(100)); //insert at index
        print("9: " + ints);
        List<Integer> sub = ints.subList(1, 4);
        print("sublist: " + sub);
        print("10: " + ints.containsAll(sub));
        Collections.sort(sub);
        print("11: " + ints.containsAll(sub));
        Collections.shuffle(sub);
        print("shuffled sublist: " + sub);
        print("12: " + ints.containsAll(sub));
        List<Integer> copy = new ArrayList<>(ints);
        sub = Arrays.asList(ints.get(1), ints.get(4));
        print("sub: " + sub);
        copy.retainAll(sub);
        print("13: " + copy);
        copy = new ArrayList<>(ints);
        copy.remove(2);
        print("14: " + copy);
        copy.removeAll(sub);
        print("15: " + copy);
        copy.set(1, rand.nextInt(100));
        print("16: " + copy);
        copy.addAll(2, sub);
        print("17: " + copy);
        print("18: " + ints.isEmpty());
        ints.clear();
        print("19: " + ints);
        print("20: " + ints.isEmpty());
        ints.addAll(Arrays.asList(new Integer[]{1002, 2003, 1015, 1001}));
        print("21: " + ints);
        Object[] o = ints.toArray();
        print("22: " + o[3]);
        Integer[] ints1 = ints.toArray(new Integer[0]);
        print("23: " + ints1[1]);
    }
}
