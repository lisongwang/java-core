package com.lisong.learn.core.interfaces;

import static com.lisong.learn.core.util.Print.print;

public class Exercise13 implements CanDoMost{

    @Override
    public void doIt() {
        print("doIt");
    }

    @Override
    public void doMore() {
        print("doMore");
    }

    @Override
    public void doFaster() {
        print("doFaster");
    }

    @Override
    public void doMost() {
        print("doMost");
    }

    public static void main(String[] args) {

        Exercise13 exe13 = new Exercise13();
        ((CanDo)exe13).doIt();
        ((CanDoMore)exe13).doMore();
        ((CanDoFaster)exe13).doFaster();
        ((CanDoMost)exe13).doMost();
    }
}

interface CanDo {
    void doIt();
}

interface CanDoMore extends CanDo {
    void doMore();
}
interface CanDoFaster extends CanDo {
    void doFaster();
}

interface CanDoMost extends CanDoMore, CanDoFaster {
    void doMost();
}