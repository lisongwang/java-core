package com.lisong.learn.core.holding;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static com.lisong.learn.core.util.Print.print;

public class Exercise16 {

    static void vowelStrings(Set<String> strings) {

        Map<Character, Integer> countMap = new HashMap<>();

        int totalCount = 0;

        Set<Character> vowelSets = new TreeSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));

        for (String s : strings) {
            int vowelCount = 0;
            for(char c : s.toCharArray()) {
                if(vowelSets.contains(c)) {
                    vowelCount++;
                    totalCount++;
                    countMap.put(c, countMap.get(c)==null? 1 : countMap.get(c)+1);
                }
            }
            print(s + ": " + vowelCount);
        }
        print("total vowels: " + totalCount);
        print(countMap);
    }

    public static void main(String[] args) {
        Set<String> strings = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        try {
            File file = new File("JavaCore/src/main/java/com/lisong/learn/core/holding/Exercise2.java");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while((line = reader.readLine()) != null) {
                String[] str = line.split("\\W+");
                for(String s : str) {
                    if(!s.equals(""))
                        strings.add(s);
                }
            }
            reader.close();
        } catch (IOException exp) {
            exp.printStackTrace();
        }

        vowelStrings(strings);
    }
}