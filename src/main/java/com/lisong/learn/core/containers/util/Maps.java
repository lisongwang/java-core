package com.lisong.learn.core.containers.util;

import com.lisong.learn.core.util.CountingGenerator;
import com.lisong.learn.core.util.RandomGenerator;

import java.util.Map;

import static com.lisong.learn.core.util.Print.print;
import static com.lisong.learn.core.util.Print.printnb;

public class Maps {

    private static void printKeys(Map<Object,Object> map) {
        printnb("size = " + map.size() + ", keys: ");
        print(map.keySet());
    }

    public static void testMap(Map<Object,Object> map) {
        print(map.getClass().getSimpleName());
        map.putAll(MapData.map(new CountingGenerator.String(5), new RandomGenerator.String(5), 15));
        map.putAll(MapData.map(new CountingGenerator.String(5), new RandomGenerator.String(5), 15));
        printKeys(map);
        print("Values: " + map.values());
        print(map);
        print("map.containsKey(): " + map.containsKey("abcde"));
        print("map.get(): " + map.get("abcde"));
        print("map.containsVaue(): " + map.containsValue("IFAED"));
        String firstKey = (String)map.keySet().iterator().next();
        print("first key in map: " + firstKey);
        map.remove(firstKey);
        printKeys(map);
        map.clear();
        print("map is empty: " + map.isEmpty());
        map.putAll(MapData.map(new CountingGenerator.String(5), new RandomGenerator.String(5), 15));
        map.keySet().removeAll(map.keySet());
        print("map is empty: " + map.isEmpty());
    }

    public static void testMapAPI(Map<String,String> map) {
        map.put("Mom","Lisa");
        map.put("Dad","John");
        map.put("Baby","Alice");
        map.put("GrandPa","Leon");
        map.put("GrandMa","Sherry");
        print(map);
        map.putIfAbsent("Son", "David");
        map.putIfAbsent("GrandPa", "AAA");
        print("Uncle exists: " + map.getOrDefault("Uncle", "None"));
        map.replace("Mom", "Lisa", "LisaNew");
        map.replace("Baby", "Lily", "LilyNew");
        map.replace("Dad", "DadNew");
        print(map);
        map.remove("GrandPa", "Leon");
        map.computeIfAbsent("Uncle", k->k+"New");
        map.computeIfPresent("Mom", (k,v)->null);
        map.compute("Baby", (k,v)->k+v);
        print(map);
        map.replaceAll((k,v)->k+v);
        map.merge("Son", "DDD", (k,v)->k+v);
        map.forEach((k,v)->printnb(k + "=" + v + " "));
    }
}