package com.lisong.learn.core.containers;


import com.lisong.learn.core.containers.util.MapData;
import com.lisong.learn.core.containers.util.SimpleHashMap;
import com.lisong.learn.core.util.CountingGenerator;
import com.lisong.learn.core.util.RandomGenerator;

import java.util.Map;

import static com.lisong.learn.core.util.Print.print;

/**
 * Combine exercise 20, exercise 21.
 */
public class Exercise21 {

    public static void main(String[] args) {

        Map<String,String> map = new SimpleHashMap<>();
        map.putAll(MapData.map(new CountingGenerator.String(5), new RandomGenerator.String(5), 20));
        map.putAll(MapData.map(new CountingGenerator.String(5), new RandomGenerator.String(5), 20));
        print(map);
    }
}