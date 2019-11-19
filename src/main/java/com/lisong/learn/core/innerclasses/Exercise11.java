package com.lisong.learn.core.innerclasses;

import com.lisong.learn.core.innerclasses.facade.Intf9;

import static com.lisong.learn.core.util.Print.print;

/**
 * Combine exercise 9, exercise 10, exercise 11.
 */
public class Exercise11 {

    public static void main(String[] args) {

        Inner11 inner11 = new Inner11();
        Intf9 intf1 = inner11.processInner();
        Intf9 intf2 = inner11.processInner2(true);
        Intf9 intf3 = inner11.processInner3();
        intf1.showSomething();
        intf2.showSomething();
        intf3.showSomething();

        //Inner11.Intf9Impl impl = (Inner11.Intf9Impl)intf3;
    }
}

class Inner11 {

    private class Intf9Impl implements Intf9 {
        @Override
        public void showSomething() {
            print("Private inner implements public interface!");
        }
    }

    Intf9 processInner() {

        class Intf9Impl implements Intf9 {
            @Override
            public void showSomething() {
                print("Show inner within method!");
            }
        }

        return new Intf9Impl();
    }

    Intf9 processInner2(boolean flag) {

        Intf9 intf9 = null;

        if(flag) {

            class Intf9Impl implements Intf9 {
                @Override
                public void showSomething() {
                    print("Show inner within scope!");
                }
            }
            intf9 = new Intf9Impl();
        }

        return intf9;
    }

    Intf9 processInner3() {
        return new Intf9Impl();
    }
}

