package com.lisong.learn.core.containers;

import com.lisong.learn.core.containers.test.ListPerformance;
import com.lisong.learn.core.containers.test.TestParam;
import com.lisong.learn.core.containers.test.Tester;
import com.lisong.learn.core.util.CountingGenerator;
import com.lisong.learn.core.util.Generators;

import java.util.*;

public class Exercise29 {

    public static void main(String[] args) {
        if(args.length > 0)
            Tester.defaultParam = TestParam.array(args);
        else
            Tester.defaultParam = TestParam.array(10,5000,100,5000,1000,1000,10000,500);
        ListPerformance<String> listP = new ListPerformance<>(new CountingGenerator.String(), null);

        Tester<List<String>> arrayTest = new Tester<List<String>>(null, listP.list.subList(1,3)) {
            @Override
            protected List<String> initialize(int size) {
                String[] strs = Generators.fillArray(String.class, new CountingGenerator.String(), size);
                return Arrays.asList(strs);
            }
        };
        arrayTest.setHeadline("Array as List");
        arrayTest.timedTest();
        listP.new ListTester<>(new ArrayList<>(), listP.list).timedTest();
        listP.new ListTester<>(new LinkedList<>(), listP.list).timedTest();
        listP.new ListTester<>(new Vector<>(), listP.list).timedTest();
        Tester<LinkedList<String>> qtest = new Tester<>(new LinkedList<>(), listP.qlist);
        qtest.setHeadline("Queue tests");
        qtest.timedTest();
    }
}