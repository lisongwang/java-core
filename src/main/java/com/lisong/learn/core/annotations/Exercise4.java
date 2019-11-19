package com.lisong.learn.core.annotations;

import com.lisong.learn.core.annotations.atunit.Test;

import java.io.FileInputStream;
import java.io.IOException;

import static com.lisong.learn.core.util.Print.printnb;

public class Exercise4 {
    private static int count = 0;
    protected final int id = count++;

    protected int method1() {
        return id;
    }

    protected String method2() {
        return "This is method2";
    }

    @Test
    private boolean t_method1() {
        printnb("test with testObject: " + this);
        return method1() == id;
    }

    @Test
    private void t_method2() {
        printnb("test with testObject: " + this);
        assert method2().contains("is");
    }

    @Test
    private void t_method3() throws IOException {
        printnb("test with testObject: " + this);
        FileInputStream file = new FileInputStream("none.txt");
    }

    @Test
    private void t_method4() {
        printnb("test with testObject: " + this);
        assert method1() == 1: "only success as the first test";
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + id;
    }
}