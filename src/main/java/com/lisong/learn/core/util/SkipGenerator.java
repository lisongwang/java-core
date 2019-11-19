package com.lisong.learn.core.util;

public class SkipGenerator {

    public static class Boolean implements Generator<java.lang.Boolean> {
        private boolean value = false;
        private int skip = 1;

        public Boolean() {}

        public Boolean(int skip) {
            this.skip = skip;
        }

        @Override
        public java.lang.Boolean next() {
            for(int i = 0; i < skip; i++)
                value = !value;
            return value;
        }
    }

    public static class Byte implements Generator<java.lang.Byte> {
        private byte value = 0;
        private int skip = 1;

        public Byte() {}

        public Byte(int skip) {
            this.skip = skip;
        }

        @Override
        public java.lang.Byte next() {
            byte result = value;
            value += skip;
            return result;
        }
    }

    public static class Short implements Generator<java.lang.Short> {
        private short value = 0;
        private int skip = 1;

        public Short() {}

        public Short(int skip) {
            this.skip = skip;
        }

        @Override
        public java.lang.Short next() {
            short result = value;
            value += skip;
            return result;
        }
    }

    public static class Character implements Generator<java.lang.Character> {
        private int index = 0;
        private int skip = 1;

        public Character() {}

        public Character(int skip) {
            this.skip = skip;
        }

        @Override
        public java.lang.Character next() {
            char result = CountingGenerator.chars[index%CountingGenerator.chars.length];
            index += skip;
            return result;
        }
    }

    public static class Integer implements Generator<java.lang.Integer> {
        private int value = 0;
        private int skip = 1;

        public Integer() {}

        public Integer(int skip) {
            this.skip = skip;
        }

        @Override
        public java.lang.Integer next() {
            int result = value;
            value += skip;
            return result;
        }
    }

    public static class Long implements Generator<java.lang.Long> {
        private long value = 0;
        private int skip = 1;

        public Long() {}

        public Long(int skip) {
            this.skip = skip;
        }

        @Override
        public java.lang.Long next() {
            long result = value;
            value += skip;
            return result;
        }
    }

    public static class Float implements Generator<java.lang.Float> {
        private float value = 0;
        private int skip = 1;

        public Float() {}

        public Float(int skip) {
            this.skip = skip;
        }

        @Override
        public java.lang.Float next() {
            float result = value;
            value += skip;
            return result;
        }
    }

    public static class Double implements Generator<java.lang.Double> {
        private double value = 0;
        private int skip = 1;

        public Double() {}

        public Double(int skip) {
            this.skip = skip;
        }

        @Override
        public java.lang.Double next() {
            double result = value;
            value += skip;
            return result;
        }
    }

    public static class String implements Generator<java.lang.String> {
        private int length = 7;
        private int skip = 1;
        private Character chars = new Character(skip);

        public String() {}

        public String(int skip, int length) {
            this.skip = skip;
            this.length = length;
            chars = new Character(skip);
        }

        public String(int skip) {
            this.skip = skip;
            chars = new Character(skip);
        }

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
