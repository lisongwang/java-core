package com.lisong.learn.core.util;

public class CountingGenerator {

    public static class Boolean implements Generator<java.lang.Boolean> {
        private boolean value = false;
        @Override
        public java.lang.Boolean next() {
            value = !value;
            return value;
        }
    }

    public static class Byte implements Generator<java.lang.Byte> {
        private byte value = 0;
        @Override
        public java.lang.Byte next() {
            return value++;
        }
    }

    public static class Short implements Generator<java.lang.Short> {
        private short value = 0;
        @Override
        public java.lang.Short next() {
            return value++;
        }
    }

    public static char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    public static class Character implements Generator<java.lang.Character> {
        private int index = 0;
        @Override
        public java.lang.Character next() {
            index = index%chars.length;
            return chars[index++];
        }
    }

    public static class Integer implements Generator<java.lang.Integer> {
        private int value = 0;
        @Override
        public java.lang.Integer next() {
            return value++;
        }
    }

    public static class Long implements Generator<java.lang.Long> {
        private long value = 0;
        @Override
        public java.lang.Long next() {
            return value++;
        }
    }

    public static class Float implements Generator<java.lang.Float> {
        private float value = 0;
        @Override
        public java.lang.Float next() {
            return value++;
        }
    }

    public static class Double implements Generator<java.lang.Double> {
        private double value = 0;
        @Override
        public java.lang.Double next() {
            return value++;
        }
    }

    public static class String implements Generator<java.lang.String> {
        private int length = 7;
        Generator<java.lang.Character> chars = new Character();

        public String(int length) {
            this.length = length;
        }

        public String() {}

        @Override
        public java.lang.String next() {
            char[] c = new char[length];
            for(int i = 0; i < length; i++) {
                c[i] = chars.next();
            }
            return new java.lang.String(c);
        }
    }
}
