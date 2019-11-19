package com.lisong.learn.core.containers;

import com.lisong.learn.core.containers.test.ListPerformance;
import com.lisong.learn.core.containers.test.Test;
import com.lisong.learn.core.containers.test.TestParam;
import com.lisong.learn.core.containers.test.Tester;
import com.lisong.learn.core.containers.util.MyArrayList;
import com.lisong.learn.core.util.RandomGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Exercise31 {

    private static Random rand = new Random(47);

    private static List<Test<MyArrayList>> list1 = new ArrayList<>();
    static {
        list1.add(new Test<MyArrayList>("add") {
            @Override
            public long test(MyArrayList container, TestParam param) {
                int loop = param.loop;
                int size = param.size;
                for(int i = 0; i < loop; i++) {
                    container.clear();
                    for(int j = 0; j < size; j++)
                        container.add("ABCDEFG");
                }
                return loop*size;
            }
        });

        list1.add(new Test<MyArrayList>("get") {
            @Override
            public long test(MyArrayList container, TestParam param) {
                int loop = param.loop*100;
                int size = param.size;
                for(int i = 0; i < loop; i++)
                    container.get(rand.nextInt(size));
                return loop;
            }
        });

        list1.add(new Test<MyArrayList>("set") {
            @Override
            public long test(MyArrayList container, TestParam param) {
                int loop = param.loop*100;
                int size = param.size;
                for(int i = 0; i < loop; i++)
                    container.set(rand.nextInt(size), "ABCDEFG");
                return loop;
            }
        });
    }

    public static void main(String[] args) {
        if(args.length > 0)
            Tester.defaultParam = TestParam.array(args);
        else
            Tester.defaultParam = TestParam.array(10,50000,100,50000,1000,20000,10000,20000);
        ListPerformance<String> listP = new ListPerformance<>(new RandomGenerator.String(), null);
        Tester.run(new ArrayList<>(), listP.list.subList(0,3));
        Tester.run(new MyArrayList(), list1);
    }
}