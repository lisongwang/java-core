package com.lisong.learn.core.containers;

import com.lisong.learn.core.containers.test.ListPerformance;
import com.lisong.learn.core.containers.test.TestParam;
import com.lisong.learn.core.containers.test.Tester;
import com.lisong.learn.core.util.RandomGenerator;

import java.util.ArrayList;
import java.util.LinkedList;

public class Exercise30 {

    public static void main(String[] args) {
        if(args.length > 0)
            Tester.defaultParam = TestParam.array(args);
        else
            Tester.defaultParam = TestParam.array(10,5000,100,5000,1000,1000,10000,500);
        ListPerformance<String> listP = new ListPerformance<>(new RandomGenerator.String(), String.CASE_INSENSITIVE_ORDER);
        listP.new ListTester<>(new ArrayList<>(), listP.slist).timedTest();
        listP.new ListTester<>(new LinkedList<>(), listP.slist).timedTest();
    }
}