package com.lisong.learn.core.containers;

import com.lisong.learn.core.containers.util.SimpleHashMap;

import java.util.*;

import static com.lisong.learn.core.util.Print.print;

/**
 * Combine exercise 26, exercise 27.
 */
public class Exercise27 {

    public static void main(String[] args) {

        Map<CountedString, Integer> map = new SimpleHashMap<>();
        CountedString[] cs = new CountedString[5];
        for(int i = 0; i < cs.length; i++) {
            cs[i] = new CountedString('A', "Hi");
            map.put(cs[i], i);
        }
        print(map);
        for(CountedString lookup : cs) {
            print("Looking up " + lookup);
            print(map.get(lookup));
        }
    }
}

class CountedString {
    private static final List<String> created = new ArrayList<>();
    private int id = 0;
    private char c;
    private String s;

    CountedString(char c, String s) {
        this.s = s == null ? "" : s;
        this.c = c;
        created.add(s);
        for(String s1 : created) {
            if(Objects.equals(this.s, s1))
                id++;
        }
    }

    @Override
    public String toString() {
        return "String: " + s + " char: " + c + " id: " + id + " hashcode(): " + hashCode();
    }

    @Override
    public int hashCode() {
        int result = 17;
        //result = 37*result + id;
        result = 37*result + c;
        result = 37*result + s.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this.getClass().isInstance(obj)) {
            CountedString cs = (CountedString)obj;
            return id == cs.id && s.equals(cs.s) && c == cs.c;
        }
        return false;
    }
}