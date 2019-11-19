package com.lisong.learn.core.containers.test;

public class TestParam {

    public final int size;
    public final int loop;

    public TestParam(int size, int loop) {
        this.size = size;
        this.loop = loop;
    }

    public static TestParam[] array(int... intVal) {

        int size = intVal.length/2;
        TestParam[] params = new TestParam[size];
        int n = 0;
        for(int i = 0; i < size; i++)
            params[i] = new TestParam(intVal[n++],intVal[n++]);
        return params;
    }

    public static TestParam[] array(String[] values) {
        int size = values.length;
        int[] intVal = new int[size];
        for(int i = 0; i < size; i++)
            intVal[i] = Integer.decode(values[i]);
        return array(intVal);
    }
}