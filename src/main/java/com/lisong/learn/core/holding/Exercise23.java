package com.lisong.learn.core.holding;

import java.util.*;

import static com.lisong.learn.core.util.Print.print;

public class Exercise23 {

    private NavigableMap<Integer, List<Integer>> resultMap = new TreeMap<>();

    private void runRandom(Map<Integer, Integer> m, int max, int times) {

        Random rand = new Random();

        for (int i = 0; i < times; i++) {
            int r = rand.nextInt(max);
            m.put(r, m.get(r) == null? 1 : m.get(r) + 1);
        }
    }

    private void fillResultMap() {

        Map<Integer, Integer> m = new HashMap<>();

        int k = 2000;
        while(k-- > 0) {
            runRandom(m, 20, 10000);
        }

        //print(m);
        for(Integer i : m.keySet()) {
            int count = m.get(i);
            List<Integer> intList = resultMap.get(count);
            if(intList == null)
                resultMap.put(count, new LinkedList<>(Arrays.asList(i)));
            else
                intList.add(i);
        }
        print(resultMap);
        print("Most often picked integer, 0~19, in 2000 tests of 10,000 random picks: " + resultMap.lastEntry().getValue());
    }

    public static void main(String[] args) {

        Exercise23 exe23 = new Exercise23();
        exe23.fillResultMap();
    }
}