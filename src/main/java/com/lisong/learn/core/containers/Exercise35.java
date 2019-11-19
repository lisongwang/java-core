package com.lisong.learn.core.containers;

import com.lisong.learn.core.containers.test.MapPerformance;
import com.lisong.learn.core.containers.test.TestParam;
import com.lisong.learn.core.containers.test.Tester;
import com.lisong.learn.core.containers.util.MapData;
import com.lisong.learn.core.containers.util.SlowMap;

import java.util.*;

public class Exercise35 {

    public static void main(String[] args) {
        if(args.length > 0)
            Tester.defaultParam = TestParam.array(args);
        else
            Tester.defaultParam = TestParam.array(10,500,100,500,1000,200,10000,100);
        MapPerformance<Integer,Integer> mapP = new MapPerformance<>(MapData.countingGen, MapData.randomGen);
        Tester.run(new HashMap<>(), mapP.list);
        Tester.run(new LinkedHashMap<>(), mapP.list);
        Tester.run(new TreeMap<>(), mapP.list);
        Tester.run(new IdentityHashMap<>(), mapP.list);
        Tester.run(new WeakHashMap<>(), mapP.list);
        Tester.run(new Hashtable<>(), mapP.list);
        Tester.run(new SlowMap<>(), mapP.list);
    }
}