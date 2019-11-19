package com.lisong.learn.core.containers;

import com.lisong.learn.core.containers.util.TupleString;

import java.util.Comparator;

public class Exercise40 {

    public static void main(String[] args) {
        TupleString.testSort(Comparator.comparing(TupleString::getSecond));
    }
}