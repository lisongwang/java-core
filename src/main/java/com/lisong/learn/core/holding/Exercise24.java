package com.lisong.learn.core.holding;

import java.util.*;

import static com.lisong.learn.core.util.Print.print;

public class Exercise24 {

    public static void main(String[] args) {

        Map<String, WordCount> map = new LinkedHashMap<>();
        map.put("Select", new WordCount("Select"));
        map.put("Delete", new WordCount("Delete"));
        map.put("Add", new WordCount("Add"));
        map.put("Remove", new WordCount("Remove"));
        map.put("Contains", new WordCount("Contains"));
        print(map);

        List<String> keyList = new LinkedList<>(map.keySet());
        Collections.sort(keyList, String.CASE_INSENSITIVE_ORDER);
        for(String s : keyList) {
            WordCount value = map.get(s);
            //remove first
            map.remove(s);
            map.put(s, value);
        }
        print(map);
    }
}
