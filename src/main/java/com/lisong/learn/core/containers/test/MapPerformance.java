package com.lisong.learn.core.containers.test;

import com.lisong.learn.core.containers.util.MapData;
import com.lisong.learn.core.containers.util.Pair;
import com.lisong.learn.core.util.Generator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MapPerformance<K,V> {

    private Generator<Pair<K,V>> countingGen;
    private Generator<Pair<K,V>> randomGen;
    public MapPerformance(Generator<Pair<K,V>> countingGen, Generator<Pair<K,V>> randomGen) {
        this.countingGen = countingGen;
        this.randomGen = randomGen;
        init();
    }
    public List<Test<Map<K,V>>> list = new ArrayList<>();
    private void init() {
        list.add(new Test<Map<K,V>>("put") {
            @Override
            public long test(Map<K,V> container, TestParam param) {
                int loop = param.loop;
                int size = param.size;
                for(int i = 0; i < loop; i++) {
                    container.clear();
                    for(int j = 0; j < size; j++)
                        container.put(countingGen.next().key,countingGen.next().value);
                }
                return loop*size;
            }
        });

        list.add(new Test<Map<K,V>>("get") {
            @Override
            public long test(Map<K,V> container, TestParam param) {
                int loop = param.loop;
                int size = param.size*2;
                for(int i = 0; i < loop; i++)
                    for(int j = 0; j < size; j++)
                        container.get(randomGen.next().key);
                return loop*size;
            }
        });

        list.add(new Test<Map<K,V>>("iterate") {
            @Override
            public long test(Map<K,V> container, TestParam param) {
                int loop = param.loop;
                int size = param.size;
                for(int i = 0; i < loop; i++) {
                    container.clear();
                    container.putAll(new MapData<>(countingGen, size));
                    Iterator<Map.Entry<K,V>> it = container.entrySet().iterator();
                    while(it.hasNext())
                        it.next();
                }
                return loop*container.size();
            }
        });
    }
}