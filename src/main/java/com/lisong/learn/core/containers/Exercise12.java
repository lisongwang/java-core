package com.lisong.learn.core.containers;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import static com.lisong.learn.core.util.Print.print;

public class Exercise12 {

    private static void testMap(Map<String,String> map) {

        map.put("sky","blue");
        map.put("grass","green");
        map.put("ocean","dancing");
        map.put("tree","tall");
        map.put("earth","brown");
        map.put("sun","warm");
        try {
            map.put("extra","object");
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        print(map);
        print(map.get("ocean"));
    }

    public static void main(String[] args) {
        testMap(new HashMap<>(6));
        testMap(new TreeMap<>());
        testMap(new LinkedHashMap<>(6));
    }
}