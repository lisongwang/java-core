package com.lisong.learn.core.holding;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static com.lisong.learn.core.util.Print.printnb;

public class Exercise17 {

    public static void main(String[] args) {

        Map<String, Gerbil> gerbils = new HashMap<>();
        gerbils.put("Fuzzy", new Gerbil(1));
        gerbils.put("Spot", new Gerbil(2));
        gerbils.put("Kerry", new Gerbil(3));

        Iterator<String> it = gerbils.keySet().iterator();
        while(it.hasNext()) {
            String key = it.next();
            printnb(key + ": ");
            gerbils.get(key).hop();
        }
    }
}
