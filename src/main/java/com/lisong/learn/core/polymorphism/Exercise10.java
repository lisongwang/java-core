package com.lisong.learn.core.polymorphism;

import static com.lisong.learn.core.util.Print.print;

/**
 * Show the static bind and dynamic bind
 */
public class Exercise10 {

    public static void main(String[] args) {

        Appender ap = new SubAppender();
        ap.say("hello");
    }
}

class Appender {

    protected void say(String s) {
        append(s);
    }

    protected void append(String s) {
        print("Append: " + s);
    }
}

class SubAppender extends Appender {

    @Override
    protected void append(String s) {
        print("Another append: " + s);
    }
}