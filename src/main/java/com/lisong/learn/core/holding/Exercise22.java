package com.lisong.learn.core.holding;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static com.lisong.learn.core.util.Print.print;

/**
 * Combine exercise 21, exercise 22.
 */
public class Exercise22 {

    public static void main(String[] args) {
        Map<String, Integer> wordMap = new HashMap<>();
        Set<WordCount> words = new TreeSet<>();
        try {
            File file = new File("JavaCore/src/main/java/com/lisong/learn/core/holding/Exercise2.java");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] str = line.split("\\W+");
                for (String s : str) {
                    if (!s.equals("")) {
                        wordMap.put(s, wordMap.get(s) == null ? 1 : wordMap.get(s) + 1);
                        WordCount word = new WordCount(s);
                        for (WordCount w : words) {
                            if(w.equals(word))
                                w.increment();
                        }
                        words.add(word);
                    }
                }
            }
            reader.close();
        } catch (IOException exp) {
            exp.printStackTrace();
        }

        List<String> list = new LinkedList<>(wordMap.keySet());
        print(list);
        Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
        print(list);
        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        for (String s : list) {
            sortedMap.put(s, wordMap.get(s));
        }
        print(sortedMap);

        //show the result by Set
        print(words);
    }
}

class WordCount implements Comparable<WordCount>{

    private String text;
    private int count = 1;

    WordCount(String word) {
        if (word == null)
            throw new IllegalArgumentException();
        this.text = word;
    }

    void increment() {
        this.count++;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof WordCount) {
            return text.equals(((WordCount) obj).text);
        }
        return false;
    }

    @Override
    public String toString() {
        return text + "=" + count;
    }

    @Override
    public int compareTo(WordCount o) {
        return String.CASE_INSENSITIVE_ORDER.compare(text, o.text);
    }
}