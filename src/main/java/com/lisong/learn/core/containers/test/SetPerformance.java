package com.lisong.learn.core.containers.test;

import com.lisong.learn.core.containers.util.CollectionData;
import com.lisong.learn.core.util.Generator;

import java.util.*;

public class SetPerformance<T> {

    private Generator<T> countingGen;
    private Generator<T> randomGen;
    public SetPerformance(Generator<T> countingGen, Generator<T> randomGen) {
        this.countingGen = countingGen;
        this.randomGen = randomGen;
        init();
    }
    public List<Test<Set<T>>> list = new ArrayList<>();
    private void init() {
        list.add(new Test<Set<T>>("add") {
            @Override
            public long test(Set<T> container, TestParam param) {
                int loop = param.loop;
                int size = param.size;
                for(int i = 0; i < loop; i++) {
                    container.clear();
                    for(int j = 0; j < size; j++)
                        container.add(countingGen.next());
                }
                return loop*size;
            }
        });

        list.add(new Test<Set<T>>("contains") {
            @Override
            public long test(Set<T> container, TestParam param) {
                int loop = param.loop;
                int size = param.size*2;
                for(int i = 0; i < loop; i++)
                    for(int j = 0; j < size; j++)
                        container.contains(randomGen.next());
                return loop*size;
            }
        });

        list.add(new Test<Set<T>>("iterate") {
            @Override
            public long test(Set<T> container, TestParam param) {
                int loop = param.loop;
                for(int i = 0; i < loop; i++) {
                    Iterator<T> it = container.iterator();
                    while(it.hasNext())
                        it.next();
                }
                return loop*container.size();
            }
        });

        list.add(new Test<Set<T>>("remove") {
            @Override
            public long test(Set<T> container, TestParam param) {
                int loop = param.loop;
                int size = param.size;
                for(int i = 0; i < loop; i++) {
                    container.clear();
                    container.addAll(new CollectionData<>(countingGen, size));
                    Iterator<T> it = container.iterator();
                    while(it.hasNext()) {
                        it.next();
                        it.remove();
                    }
                }
                return loop*size;
            }
        });
    }
}