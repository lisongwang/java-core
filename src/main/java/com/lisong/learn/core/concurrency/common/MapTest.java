package com.lisong.learn.core.concurrency.common;

import java.util.Map;

public abstract class MapTest extends Tester<Map<Integer,Integer>> {

    public MapTest(String testID, int nReaders, int nWriters) {
        super(testID, nReaders, nWriters);
    }

    class Reader extends TestTask {
        long result = 0;
        @Override
        void test() {
            for(int i = 0; i < testCycles; i++) {
                for(int j = 0; j < containerSize; j++) {
                    result += testContainer.get(j);
                }
            }
        }

        @Override
        void putResults() {
            readResult += result;
            readTime += duration;
        }
    }

    class Writer extends TestTask {
        @Override
        void test() {
            for(int i = 0; i < testCycles; i++) {
                for(int j = 0; j < containerSize; j++) {
                    testContainer.put(j,writeData[j]);
                }
            }
        }

        @Override
        void putResults() {
            writeTime += duration;
        }
    }

    @Override
    protected void startReaderAndWriter() {
        for(int i = 0; i < nReaders; i++)
            es.execute(new Reader());
        for(int i = 0; i < nWriters; i++)
            es.execute(new Writer());
    }
}