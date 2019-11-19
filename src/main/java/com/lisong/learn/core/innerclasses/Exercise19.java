package com.lisong.learn.core.innerclasses;

import static com.lisong.learn.core.util.Print.print;

public class Exercise19 {

    public static void main(String[] args) {

        Outer19 out19 = new Outer19();
        Outer19.Inner19 in19 = out19.new Inner19();
        Outer19.Inner19.InnerInner19 inin19 = in19.new InnerInner19();

        Outer19.Nested19 nest19 = new Outer19.Nested19();
        Outer19.Nested19.NestedNested19 nestnest19 = new Outer19.Nested19.NestedNested19();
    }
}

class Outer19 {

    Outer19() { print("Constructing Outer19"); }

     class Inner19 {

         Inner19() { print("Constructing Inner19"); }

        class InnerInner19 {
            InnerInner19() { print("Constructing InnerInner19"); }
        }
    }

    static class Nested19 {

        Nested19() { print("Constructing Nested19"); }

         static class NestedNested19 {
             NestedNested19() { print("Constructing NestedNested19"); }
         }
    }
}