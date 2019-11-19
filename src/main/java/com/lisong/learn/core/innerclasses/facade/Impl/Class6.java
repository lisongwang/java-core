package com.lisong.learn.core.innerclasses.facade.Impl;

import com.lisong.learn.core.innerclasses.facade.Intf6;

import static com.lisong.learn.core.util.Print.print;

public class Class6 {

    protected class intf6Impl implements Intf6 {

        public intf6Impl() {}

        @Override
        public void doSomething() {
            print("Do something!");
        }
    }
}
