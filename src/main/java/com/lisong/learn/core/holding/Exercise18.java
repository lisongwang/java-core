package com.lisong.learn.core.holding;

import java.util.*;

import static com.lisong.learn.core.util.Print.print;

public class Exercise18 {

    public static void main(String[] args) {

        Map<String, Gerbil> gerbils = new HashMap<>();
        gerbils.put("Fuzzy", new Gerbil(1));
        gerbils.put("Spot", new Gerbil(2));
        gerbils.put("Kerry", new Gerbil(3));
        gerbils.put("Funny", new Gerbil(4));
        gerbils.put("Silly", new Gerbil(5));
        print(gerbils);

        Set<String> sortedKey = new TreeSet<>(gerbils.keySet());
        Map<String, Gerbil> sortedMap = new LinkedHashMap<>();
        print(sortedKey);
        for(String s : sortedKey) {
            sortedMap.put(s, gerbils.get(s));
        }
        print(sortedMap);
    }
}
