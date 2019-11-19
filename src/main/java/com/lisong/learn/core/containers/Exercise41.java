package com.lisong.learn.core.containers;

import com.lisong.learn.core.containers.util.MapData;
import com.lisong.learn.core.containers.util.SimpleHashMap3;
import com.lisong.learn.core.containers.util.SimpleHashSet;
import com.lisong.learn.core.containers.util.TupleString;
import com.lisong.learn.core.util.Generators;
import com.lisong.learn.core.util.RandomGenerator;

import java.util.*;

import static com.lisong.learn.core.util.Print.print;

public class Exercise41 {

    public static void main(String[] args) {
        List<TupleString> list = Generators.fill(new ArrayList<>(),
                ()->new TupleString(MapData.randStrGen.next(),MapData.randStrGen.next()), 7);
        Set<TupleString> set = new SimpleHashSet<>();
        set.addAll(list);
        set.addAll(list);
        print("set: " + set);
        List<TupleString> list2 = list.subList(2,5);
        print("list2: " + list2);
        set.retainAll(list2);
        print("set: " + set);
        set.clear();
        print("size(): " + set.size());

        Map<TupleString, Integer> map1 = new HashMap<>(MapData.map(
                ()->new TupleString(MapData.countStrGen.next(), MapData.countStrGen.next()), new RandomGenerator.Integer(), 7));
        Map<TupleString, Integer> map = new SimpleHashMap3<>();
        map.putAll(map1);
        map.putAll(map1);
        print("map: " + map);
        TupleString key = new TupleString("EFGHI","JKLMN");
        print("contains: " + key + "? " + map.containsKey(key));
    }
}