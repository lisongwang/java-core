package com.lisong.learn.core.containers;

import com.lisong.learn.core.containers.test.SetPerformance;
import com.lisong.learn.core.containers.test.TestParam;
import com.lisong.learn.core.containers.test.Tester;
import com.lisong.learn.core.util.CountingGenerator;
import com.lisong.learn.core.util.RandomGenerator;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

public class Exercise34 {

    public static void main(String[] args) {
        if(args.length > 0)
            Tester.defaultParam = TestParam.array(args);
        else
            Tester.defaultParam = TestParam.array(10,5000,100,5000,1000,5000,10000,1000);
        SetPerformance<String> setP = new SetPerformance<>(new CountingGenerator.String(), new RandomGenerator.String());
        Tester.run(new HashSet<>(), setP.list);
        Tester.run(new LinkedHashSet<>(), setP.list);
        Tester.run(new TreeSet<>(), setP.list);
    }
}