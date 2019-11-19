package com.lisong.learn.core.containers;

import com.lisong.learn.core.containers.util.Countries;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.lisong.learn.core.util.Print.print;

public class Exercise2 {

    public static void main(String[] args) {

        Set resultSet = Countries.names().stream().filter(key->key.startsWith("A")).collect(Collectors.toSet());
        print(resultSet);

        Pattern p = Pattern.compile("A[a-zA-Z]*");
        Map<String,String> resultMap = new HashMap<>(Countries.capitals());
        for(String name : Countries.names()) {
            if(!p.matcher(name).lookingAt())
                resultMap.remove(name);
        }
        print(resultMap);
    }
}
