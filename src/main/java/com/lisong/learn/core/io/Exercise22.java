package com.lisong.learn.core.io;

import com.lisong.learn.core.io.util.OSExecute;

import static com.lisong.learn.core.util.Print.pprint;

public class Exercise22 {

    public static void main(String[] args) {
        pprint(OSExecute.command(
                "javap -cp /Users/gavin/IdeaProjects/LearnJava/JavaCore/target/classes com.lisong.learn.core.io.Exercise22"));
    }
}