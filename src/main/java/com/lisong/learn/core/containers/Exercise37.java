package com.lisong.learn.core.containers;

import com.lisong.learn.core.containers.test.MapPerformance;
import com.lisong.learn.core.containers.test.TestParam;
import com.lisong.learn.core.containers.test.Tester;
import com.lisong.learn.core.containers.util.MapData;
import com.lisong.learn.core.containers.util.SimpleHashMap;
import com.lisong.learn.core.containers.util.SimpleHashMap2;
import com.lisong.learn.core.containers.util.SimpleHashMap3;

public class Exercise37 {

    public static void main(String[] args) {
        if(args.length > 0)
            Tester.defaultParam = TestParam.array(args);
        else
            Tester.defaultParam = TestParam.array(10,500,100,500,1000,200,10000,100);
        MapPerformance<Integer,Integer> mapP = new MapPerformance<>(MapData.countingGen, MapData.randomGen);
        Tester.run(new SimpleHashMap<>(), mapP.list);
        Tester.run(new SimpleHashMap2<>(), mapP.list);
        Tester.run(new SimpleHashMap3<>(), mapP.list);
    }
}