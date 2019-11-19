package com.lisong.learn.core.containers;

import com.lisong.learn.core.containers.util.MySortedSet;
import com.lisong.learn.core.containers.util.SimpleHashMap;
import com.lisong.learn.core.containers.util.SlowMap;
import com.lisong.learn.core.generics.tuple.FourTuple;
import com.lisong.learn.core.generics.tuple.Tuple;
import com.lisong.learn.core.generics.tuple.TwoTuple;

import java.util.Map;
import java.util.Set;

import static com.lisong.learn.core.util.Print.print;

public class Exercise28 {

    private static void testEquals() {
        Map<FourTuple<String,String,Long,Double>, Integer> map = new SlowMap<>();
        map.put(Tuple.tuple("December","Friday", 12L, 24.356), 1);
        map.put(Tuple.tuple("October","Friday", 12L, 24.356), 2);
        map.put(Tuple.tuple("December","Tuesday", 12L, 24.356), 3);
        map.put(Tuple.tuple("December","Friday", 9L, 24.356), 4);
        map.put(Tuple.tuple("December","Friday", 12L, 2.356), 5);
        map.put(Tuple.tuple("December","Friday", 12L, 24.356), 6);
        map.put(Tuple.tuple("October","Friday", 12L, 24.356), 7);
        map.put(Tuple.tuple("December","Tuesday", 12L, 24.356), 8);
        map.put(Tuple.tuple("December","Friday", 9L, 24.356), 9);
        map.put(Tuple.tuple("December","Friday", 12L, 2.356), 10);
        print(map);
    }

    private static void testHashCode(Map<TwoTuple<Integer,Integer>,Integer> map) {
        map.put(Tuple.tuple(10,10,10,10,10, 0), 1);
        map.put(Tuple.tuple(10,10,10,10,10), 2);
        map.put(Tuple.tuple(9,9,9,null), 3);
        map.put(Tuple.tuple(9,9,9), 4);
        map.put(Tuple.tuple(8,8,null), 5);
        map.put(Tuple.tuple(8,8), 6);
        map.put(Tuple.tuple(7,7), 7);
        print(map);
    }

    private static void testComparable() {
        Set<TwoTuple<Integer,Integer>> set = new MySortedSet<>();
        set.add(Tuple.tuple (1,2));
        set.add(Tuple.tuple (1,22));
        set.add(Tuple.tuple (1,11));
        set.add(Tuple.tuple (2,5));
        set.add(Tuple.tuple (2,55));
        set.add(Tuple.tuple (2,2));
        set.add(Tuple.tuple (9,1));
        set.add(Tuple.tuple (4,80));
        set.add(Tuple.tuple (7,50));
        set.add(Tuple.tuple (3,20));
        print(set);
    }

    private static void testUnComparable() {
        Set<TwoTuple<Integer,Integer>> set = new MySortedSet<>();
        set.add(Tuple.tuple(9,9));
        set.add(Tuple.tuple(9,9,9));
        set.add(Tuple.tuple(9,9,9,9));
        set.add(Tuple.tuple(9,9,9,9, 9));
        set.add(Tuple.tuple(9,9,9,9, 9, 9));
        print(set);
    }

    private static void testNull() {
        Set<TwoTuple<Integer,Integer>> set = new MySortedSet<>();
        set.add(Tuple.tuple(9,9,9));
        set.add(Tuple.tuple(9,9,null));
    }

    public static void main(String[] args) {
        testEquals();
        testHashCode(new SlowMap<>());
        testHashCode(new SimpleHashMap<>());
        testComparable();
        testUnComparable();
        testNull();
    }
}