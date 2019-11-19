package com.lisong.learn.core.containers;

import com.lisong.learn.core.containers.test.MapPerformance;
import com.lisong.learn.core.containers.test.Test;
import com.lisong.learn.core.containers.test.TestParam;
import com.lisong.learn.core.containers.test.Tester;
import com.lisong.learn.core.containers.util.MapData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Exercise38 {
    private static int capacity = 16;
    private static float loadFactor = 0.75f;

    private static class MapTester extends Tester<Map<Integer,Integer>> {
        @Override
        protected Map<Integer,Integer> initialize(int size) {
            Map<Integer,Integer> newMap = new HashMap<>(capacity, loadFactor);
            newMap.putAll(new MapData<>(MapData.countingGen, size));
            return newMap;
        }

        MapTester(Map<Integer,Integer> container, List<Test<Map<Integer,Integer>>> tests) {
            super(container, tests);
        }
    }

    public static void main(String[] args) {
        if(args.length > 0)
            Tester.defaultParam = TestParam.array(args);
        else
            Tester.defaultParam = TestParam.array(500,5000,5000,5000,10000,5000);
        MapPerformance<Integer,Integer> mapP = new MapPerformance<>(MapData.countingGen, MapData.randomGen);
        Tester<Map<Integer,Integer>> test1 = new MapTester(null, mapP.list.subList(1,2));
        test1.setHeadline("First Test");
        test1.timedTest();
        Tester<Map<Integer,Integer>> test2 = new MapTester(null, mapP.list.subList(1,2));
        capacity = 1024;
        loadFactor = 0.9f;
        test2.setHeadline("Second Test");
        test2.timedTest();
    }
}