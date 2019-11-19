package com.lisong.learn.core.containers.test;

import java.util.List;

public class Tester<C> {

    public static TestParam[] defaultParam = TestParam.array(10,5000,100,5000,1000,5000,10000,5000);
    private List<Test<C>> tests;
    protected C container;
    protected C initialize(int size) { return container; }
    protected TestParam[] paramList = defaultParam;

    private static int fieldWidth = 12;
    private static int sizeWidth = 8;
    private String headlines = "";

    private String stringField() { return "%" + fieldWidth + "s"; }
    private String numberField() { return "%" + fieldWidth + "d"; }
    private static String sizeField = "%" + sizeWidth + "s";

    public Tester(C container, List<Test<C>> tests) {
        this.container = container;
        this.tests = tests;
        if(container != null)
            headlines = container.getClass().getSimpleName();
    }
    public Tester(C container, List<Test<C>> tests, TestParam[] paramlist) {
        this(container,tests);
        this.paramList = paramlist;
    }

    public static <C> void run(C container, List<Test<C>> tests, TestParam[] paramlist) {
        new Tester<>(container, tests, paramlist).timedTest();
    }

    public static <C> void run(C container, List<Test<C>> tests) {
        new Tester<>(container, tests).timedTest();
    }

    public void setHeadline(String newHeadline) { this.headlines = newHeadline; }

    public void timedTest() {
        printHeader();
        for(TestParam param : paramList) {
            System.out.format(sizeField, param.size);
            for(Test<C> test : tests) {
                C c = initialize(param.size);
                long start = System.nanoTime();
                long rep = test.test(c,param);
                long duration = System.nanoTime() - start;
                long timePerRep = duration/rep;
                System.out.format(numberField(), timePerRep);
            }
            System.out.println();
        }
    }

    private void printHeader() {
        int total = sizeWidth + tests.size()*fieldWidth;
        int half = (total - headlines.length() - 1)/2;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < half; i++)
            sb.append("-");
        sb.append(" ");
        sb.append(headlines);
        sb.append(" ");
        for(int i = 0; i < half; i++)
            sb.append("-");
        System.out.println(sb.toString());
        System.out.format(sizeField, "size");
        for(Test<C> test : tests)
            System.out.format(stringField(), test.name);
        System.out.println();
    }
}