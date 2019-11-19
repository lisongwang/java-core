package com.lisong.learn.core.generics;

import static com.lisong.learn.core.util.Print.print;

public class Exercise23 {

    public static void main(String[] args) {

        print(new Foo2<String>("StringFactory", String::new).x);
        print(new Foo2<Widget>("Widget", Widget::new).x.name);
    }
}

interface IFactory<T> {
    T create(String name);
}

class Foo2<T> {

    T x;
    <F extends IFactory<T>> Foo2(String name, F factory) {
        x = factory.create(name);
    }
}

class Widget {
    String name;
    Widget(String name) {
        this.name = name;
    }
}