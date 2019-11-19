package com.lisong.learn.core.innerclasses;

import com.lisong.learn.core.innerclasses.facade.Impl.Class6;
import com.lisong.learn.core.innerclasses.facade.Intf6;

public class Exercise6 extends Class6 {

    Intf6 getIntf6() {
        return this.new intf6Impl();
    }

    public static void main(String[] args) {

        Exercise6 exe6 = new Exercise6();
        exe6.getIntf6().doSomething();
    }
}
