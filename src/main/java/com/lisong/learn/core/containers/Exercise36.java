package com.lisong.learn.core.containers;

import com.lisong.learn.core.containers.test.MapPerformance;
import com.lisong.learn.core.containers.test.TestParam;
import com.lisong.learn.core.containers.test.Tester;
import com.lisong.learn.core.containers.util.MapData;
import com.lisong.learn.core.containers.util.SlowMap2;
import com.lisong.learn.core.containers.util.SlowMap3;

public class Exercise36 {

    public static void main(String[] args) {
        if(args.length > 0)
            Tester.defaultParam = TestParam.array(args);
        else
            Tester.defaultParam = TestParam.array(10,500,100,500,1000,2000);
        MapPerformance<Integer,Integer> mapP = new MapPerformance<>(MapData.countingGen, MapData.randomGen);
        Tester.run(new SlowMap2<>(), mapP.list);
        Tester.run(new SlowMap3<>(), mapP.list);
    }
}