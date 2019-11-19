package com.lisong.learn.core.arrays;

import com.lisong.learn.core.util.Generator;
import com.lisong.learn.core.util.Generators;

import java.util.Arrays;

import static com.lisong.learn.core.util.Print.print;

public class Exercise17 {

    static class BigDecimal implements Generator<java.math.BigDecimal> {
        private java.math.BigDecimal d = new java.math.BigDecimal(0.0);
        private static final java.math.BigDecimal ONE = new java.math.BigDecimal(1.0);
        @Override
        public java.math.BigDecimal next() {
            return d = d.add(ONE);
        }
    }

    public static void main(String[] args) {

        print(Arrays.toString(Generators.fillArray(new java.math.BigDecimal[5], new BigDecimal())));
        print(Arrays.toString(Generators.fillArray(java.math.BigDecimal.class, new BigDecimal(), 10)));
    }
}
