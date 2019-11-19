package com.lisong.learn.core.holding;

import java.util.*;

import static com.lisong.learn.core.util.Print.print;

/**
 * Combine exercise 19, exercise 20
 */
public class Exercise20 {

    public static void main(String[] args) {

        Map<String, String> dictionary = new HashMap<>();
        dictionary.put("S", "Success");
        dictionary.put("F", "Failed");
        dictionary.put("P", "Pending");
        dictionary.put("R", "Running");
        dictionary.put("E", "Exit");
        print(dictionary);
        Set<String> sortedKey = new LinkedHashSet<>(dictionary.keySet());
        print(sortedKey);
        Map<String, String> sortedDic = new LinkedHashMap<>();
        for(String s : sortedKey) {
            sortedDic.put(s, dictionary.get(s));
        }
        print(sortedDic);
    }
}
