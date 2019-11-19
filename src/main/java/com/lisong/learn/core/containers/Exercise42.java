package com.lisong.learn.core.containers;

import com.lisong.learn.core.containers.util.TupleString;

public class Exercise42 {

    public static void main(String[] args) {
        TupleString.testSort((ts1, ts2)->String.CASE_INSENSITIVE_ORDER.compare(ts1.getSecond(),ts2.getSecond()));
    }
}