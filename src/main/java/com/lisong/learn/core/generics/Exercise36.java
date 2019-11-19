package com.lisong.learn.core.generics;

import java.util.ArrayList;
import java.util.List;

import static com.lisong.learn.core.util.Print.print;

public class Exercise36 {

    public static void main(String[] args) {

        ProcessRunner<String, Failure1, Failure3> runner1 = new ProcessRunner<>();
        ProcessRunner<Integer, Failure2, Failure3> runner2 = new ProcessRunner<>();
        ProcessRunner<String, Failure1, Failure3> runner1b = new ProcessRunner<>();
        ProcessRunner<Integer, Failure2, Failure3> runner2b = new ProcessRunner<>();
        for(int i = 0; i < 3; i++) {
            runner1.add(new Processor1());
            runner1b.add(new Processor1b());
            runner2.add(new Processor2());
            runner2b.add(new Processor2b());
        }

        try {
            print(runner1.processAll());
        } catch (Failure1 failure1) {
            print("Failure1 from runner1.");
        } catch (Failure3 failure3) {
            print("Failure3 from runner1.");
        }

        try {
            print(runner2.processAll());
        } catch (Failure2 failure2) {
            print("Failure2 from runner2.");
        } catch (Failure3 failure3) {
            print("Failure3 from runner2.");
        }

        try {
            print(runner1b.processAll());
        } catch (Failure1 failure1) {
            print("Failure1 from runner1b.");
        } catch (Failure3 failure3) {
            print("Failure3 from runner1b.");
        }

        try {
            print(runner2b.processAll());
        } catch (Failure2 failure2) {
            print("Failure2 from runner2b.");
        } catch (Failure3 failure3) {
            print("Failure3 from runner2b.");
        }
    }
}

interface Processor<T,E1 extends Exception, E2 extends Exception> {

    void process(List<T> resultList) throws E1,E2;
}

class ProcessRunner<T,E1 extends Exception,E2 extends Exception> extends ArrayList<Processor<T,E1,E2>> {

    List<T> processAll() throws E1,E2 {
        List<T> resultList = new ArrayList<>();
        for(Processor<T,E1,E2> p : this)
            p.process(resultList);
        return resultList;
    }
}

class Failure1 extends Exception {}
class Processor1 implements Processor<String, Failure1, Failure3> {
    private static int count = 3;
    @Override
    public void process(List<String> resultList) throws Failure1,Failure3 {
        if(count-- > 1)
            resultList.add("Run");
        else
            resultList.add("End");
        if (count == 0)
            throw new Failure1();
        else if(count < -1)
            throw new Failure3();
    }
}
class Processor1b implements Processor<String, Failure1, Failure3> {
    private static int count = 3;
    @Override
    public void process(List<String> resultList) throws Failure1,Failure3 {
        if(count-- > 1)
            resultList.add("Run");
        else
            resultList.add("End");
        if (count < 0)
            throw new Failure1();
        else if(count == 1)
            throw new Failure3();
    }
}

class Failure2 extends Exception {}
class Processor2 implements Processor<Integer, Failure2, Failure3> {
    private static int count = 2;
    @Override
    public void process(List<Integer> resultList) throws Failure2,Failure3 {
        if(count-- > 1)
            resultList.add(200);
        else
            resultList.add(404);
        if (count == 0)
            throw new Failure2();
        else if(count < -1)
            throw new Failure3();
    }
}

class Processor2b implements Processor<Integer, Failure2, Failure3> {
    private static int count = 2;
    @Override
    public void process(List<Integer> resultList) throws Failure2,Failure3 {
        if(count-- > 1)
            resultList.add(200);
        else
            resultList.add(404);
        if (count < 0)
            throw new Failure2();
        else if(count == 1)
            throw new Failure3();
    }
}

class Failure3 extends Exception {}