package com.lisong.learn.core.concurrency;

import com.lisong.learn.core.concurrency.common.MapTest;
import com.lisong.learn.core.concurrency.common.Tester;
import com.lisong.learn.core.containers.util.MapData;
import com.lisong.learn.core.util.CountingGenerator;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Exercise40 {

    public static void main(String[] args) {
        Tester.initMain(args);
        new SynchronizedHashMapTest(10,0);
        new SynchronizedHashMapTest(9,1);
        new SynchronizedHashMapTest(5,5);
        new ConcurrentHashMapTest(10,0);
        new ConcurrentHashMapTest(9,1);
        new ConcurrentHashMapTest(5,5);
        new ReaderWriterMapTest(10,0);
        new ReaderWriterMapTest(9,1);
        new ReaderWriterMapTest(5,5);
        Tester.es.shutdown();
    }
}

class ReaderWriterMap<K,V> extends HashMap<K,V> {

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock(false);

    public ReaderWriterMap(Map<? extends K, ? extends V> m) {
        super(m);
    }

    @Override
    public V get(Object key) {
        Lock rlock = lock.readLock();
        rlock.lock();
        try {
            return super.get(key);
        }finally {
            rlock.unlock();
        }
    }

    @Override
    public V put(K k, V v) {
        Lock wlock = lock.writeLock();
        wlock.lock();
        try {
            return super.put(k,v);
        }finally {
            wlock.unlock();
        }
    }
}

class SynchronizedHashMapTest extends MapTest {

    public Map<Integer, Integer> containerInitializer() {
        return Collections.synchronizedMap(
                new HashMap<>(MapData.map(
                        new CountingGenerator.Integer(),new CountingGenerator.Integer(),containerSize)));
    }

    SynchronizedHashMapTest(int nReaders, int nWriters) {
        super("SynchronizedHashMap", nReaders, nWriters);
    }
}

class ConcurrentHashMapTest extends MapTest {

    public Map<Integer, Integer> containerInitializer() {
        return new ConcurrentHashMap<>(MapData.map(
                new CountingGenerator.Integer(),new CountingGenerator.Integer(),containerSize));
    }

    ConcurrentHashMapTest(int nReaders, int nWriters) {
        super("ConcurrentHashMap", nReaders, nWriters);
    }
}

class ReaderWriterMapTest extends MapTest {

    public ReaderWriterMap<Integer,Integer> containerInitializer() {
        return new ReaderWriterMap<>(MapData.map(
                new CountingGenerator.Integer(),new CountingGenerator.Integer(),containerSize));
    }

    ReaderWriterMapTest(int nReaders, int nWriters) {
        super("ReaderWriterMap", nReaders, nWriters);
    }
}