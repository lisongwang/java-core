package com.lisong.learn.core.holding;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static com.lisong.learn.core.util.Print.print;

/**
 * Combine exercise 25, exercise 26.
 */
public class Exercise26 {

    public static void main(String[] args) {
        Map<String, List<Integer>> wordMap = new LinkedHashMap<>();
        try {
            File file = new File("JavaCore/src/main/java/com/lisong/learn/core/holding/Exercise2.java");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            int linePos = 0;
            while ((line = reader.readLine()) != null) {
                String[] str = line.split("\\W+");
                for (int i = 0; i < str.length; i++) {
                    if (!str[i].equals("")) {
                        linePos++;
                        String s = str[i];
                        List<Integer> countList = wordMap.get(s);
                        if(countList == null)
                            wordMap.put(s, new LinkedList<>(Arrays.asList(linePos)));
                        else
                            countList.add(linePos);
                    }
                }
            }
            reader.close();
        } catch (IOException exp) {
            exp.printStackTrace();
        }

        print(wordMap);

        Map<Integer, String> orderMap = new TreeMap<>();
        Iterator<Map.Entry<String, List<Integer>>> it = wordMap.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<String, List<Integer>> entry = it.next();
            for (int i : entry.getValue())
                orderMap.put(i, entry.getKey());
        }

        print(orderMap.values());
    }
}