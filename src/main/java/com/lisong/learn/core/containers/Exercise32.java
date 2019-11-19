package com.lisong.learn.core.containers;

import com.lisong.learn.core.containers.test.ListPerformance;
import com.lisong.learn.core.containers.test.Test;
import com.lisong.learn.core.containers.test.TestParam;
import com.lisong.learn.core.containers.test.Tester;
import com.lisong.learn.core.containers.util.MyArrayList2;
import com.lisong.learn.core.util.CountingGenerator;
import com.lisong.learn.core.util.Generator;
import com.lisong.learn.core.util.RandomGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Exercise32 {

    private static Generator<Integer> gen = new CountingGenerator.Integer();
    private static Random rand = new Random(47);
    private static List<Test<MyArrayList2>> list1 = new ArrayList<>();
    static {
        list1.add(new Test<MyArrayList2>("add") {
            @Override
            public long test(MyArrayList2 container, TestParam param) {
                int loop = param.loop;
                int size = param.size;
                for(int i = 0; i < loop; i++) {
                    container.clear();
                    for(int j = 0; j < size; j++)
                        container.add(100);
                }
                return loop*size;
            }
        });

        list1.add(new Test<MyArrayList2>("get") {
            @Override
            public long test(MyArrayList2 container, TestParam param) {
                int loop = param.loop*100;
                int size = param.size;
                for(int i = 0; i < loop; i++)
                    container.get(rand.nextInt(size));
                return loop;
            }
        });

        list1.add(new Test<MyArrayList2>("set") {
            @Override
            public long test(MyArrayList2 container, TestParam param) {
                int loop = param.loop*100;
                int size = param.size;
                for(int i = 0; i < loop; i++)
                    container.set(rand.nextInt(size), 100);
                return loop;
            }
        });

        list1.add(new Test<MyArrayList2>("inc") {
            @Override
            public long test(MyArrayList2 container, TestParam param) {
                int loop = param.loop;
                int size = param.size;
                for(int i = 0; i < loop; i++) {
                    for(int j = 0; j < size; j++)
                        container.increment(j);
                }
                return loop*size;
            }
        });
    }

    private static ListPerformance<Integer> listP = new ListPerformance<>(new RandomGenerator.Integer(), null);
    private static List<Test<List<Integer>>> list2 = new ArrayList<>();
    static {
        list2.addAll(listP.list.subList(0,3));
        list2.add(new Test<List<Integer>>("inc") {
            @Override
            public long test(List<Integer> container, TestParam param) {
                int loop = param.loop;
                int size = param.size;
                for(int i = 0; i < loop; i++)
                    container.replaceAll(k->k+1);
                return loop*size;
            }
        });
    }

    public static void main(String[] args) {
        if(args.length > 0)
            Tester.defaultParam = TestParam.array(args);
        else
            Tester.defaultParam = TestParam.array(10,50000,100,50000,1000,20000,10000,20000,100000,10000);
        Tester.run(new ArrayList<>(), list2);
        Tester.run(new MyArrayList2(), list1);
    }
}