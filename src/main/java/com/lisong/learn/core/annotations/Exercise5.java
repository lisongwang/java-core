package com.lisong.learn.core.annotations;

import com.lisong.learn.core.annotations.atunit.Test;

import java.io.FileInputStream;
import java.io.IOException;

import static com.lisong.learn.core.util.Print.printnb;

public class Exercise5 extends Exercise4 {

    @Test
    private boolean i_method1() {
        printnb("test with testObject: " + this);
        return method1() == id;
    }

    @Test
    private void i_method2() {
        printnb("test with testObject: " + this);
        assert method2().contains("is");
    }

    @Test
    private void i_method3() throws IOException {
        printnb("test with testObject: " + this);
        FileInputStream file = new FileInputStream("none.txt");
    }

    @Test
    private void i_method4() {
        printnb("test with testObject: " + this);
        assert method1() == 1: "only success as the first test";
    }
}