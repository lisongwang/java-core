package com.lisong.learn.core.containers.util;

import com.lisong.learn.core.util.CountingGenerator;
import com.lisong.learn.core.util.Generator;
import com.lisong.learn.core.util.RandomGenerator;

import java.util.LinkedHashMap;

public class MapData<K,V> extends LinkedHashMap<K,V> {

    public static class PairGenerator<K,V> implements Generator<Pair<K,V>> {
        private Generator<K> keyGen;
        private Generator<V> valueGen;

        PairGenerator(Generator<K> keyGen, Generator<V> valueGen) {
            this.keyGen = keyGen;
            this.valueGen = valueGen;
        }

        @Override
        public Pair<K, V> next() {
            return new Pair<>(keyGen.next(),valueGen.next());
        }
    }

    public static Generator<Pair<Integer,Integer>> countingGen = new PairGenerator<>(
            new CountingGenerator.Integer(),new CountingGenerator.Integer());
    public static Generator<Pair<Integer,Integer>> randomGen = new PairGenerator<>(
            new RandomGenerator.Integer(),new RandomGenerator.Integer());
    public static Generator<String> randStrGen = new RandomGenerator.String(5);
    public static Generator<String> countStrGen = new CountingGenerator.String(5);

    public MapData(Generator<Pair<K,V>> gen, int quantity) {
        for(int i = 0; i < quantity; i++) {
            Pair<K,V> p = gen.next();
            put(p.key, p.value);
        }
    }

    public MapData(Generator<K> keyGen, Generator<V> valueGen, int quantity) {
        for(int i = 0; i < quantity; i++) {
            put(keyGen.next(), valueGen.next());
        }
    }

    public MapData(Generator<K> keyGen, V value, int quantity) {
        for(int i = 0; i < quantity; i++) {
            put(keyGen.next(), value);
        }
    }

    public MapData(Iterable<K> keyGen, Generator<V> valueGen) {
        for(K key : keyGen) {
            put(key, valueGen.next());
        }
    }

    public MapData(Iterable<K> keyGen, V value) {
        for(K key : keyGen) {
            put(key, value);
        }
    }

    public static <K,V> MapData<K,V> map(Generator<Pair<K,V>> gen, int quantity) {
        return new MapData<>(gen, quantity);
    }

    public static <K,V> MapData<K,V> map(Generator<K> keyGen, Generator<V> valueGen, int quantity) {
        return new MapData<>(keyGen, valueGen, quantity);
    }

    public static <K,V> MapData<K,V> map(Generator<K> keyGen, V value, int quantity) {
        return new MapData<>(keyGen, value, quantity);
    }

    public static <K,V> MapData<K,V> map(Iterable<K> keyGen, Generator<V> valueGen) {
        return new MapData<>(keyGen, valueGen);
    }

    public static <K,V> MapData<K,V> map(Iterable<K> keyGen, V value) {
        return new MapData<>(keyGen, value);
    }
}