package com.lisong.learn.core.containers;

import com.lisong.learn.core.containers.util.SlowMap;
import com.lisong.learn.core.util.TextFile;

import java.io.File;
import java.util.List;
import java.util.Map;

import static com.lisong.learn.core.util.Print.print;

public class Exercise15 {

    public static void main(String[] args) {

        String path = "JavaCore/src/main/java/com/lisong/learn/core/containers/Exercise15.java";
        Map<String,Integer> resultMap = new SlowMap<>();
        List<String> wordList = TextFile.readWords(new File(path));
        for(String word : wordList)
            resultMap.put(word, resultMap.get(word) == null ? 1 : resultMap.get(word)+1);
        print(resultMap);
    }
}