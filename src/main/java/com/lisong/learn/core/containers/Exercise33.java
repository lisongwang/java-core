package com.lisong.learn.core.containers;

import com.lisong.learn.core.containers.test.ListPerformance;
import com.lisong.learn.core.containers.test.TestParam;
import com.lisong.learn.core.containers.test.Tester;
import com.lisong.learn.core.containers.util.FastTraversalLinkedList;
import com.lisong.learn.core.util.RandomGenerator;

import java.util.ArrayList;

public class Exercise33 {

    public static void main(String[] args) {
        if(args.length > 0)
            Tester.defaultParam = TestParam.array(args);
        else
            Tester.defaultParam = TestParam.array(10,100,100,100,1000,50,10000,10);
        ListPerformance<String> listP = new ListPerformance<>(new RandomGenerator.String(), null);
        listP.new ListTester<>(new ArrayList<>(), listP.list).timedTest();
        listP.new ListTester<>(new FastTraversalLinkedList<>(), listP.list).timedTest();
    }
}