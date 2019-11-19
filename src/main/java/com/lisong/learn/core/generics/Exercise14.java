package com.lisong.learn.core.generics;

import com.lisong.learn.core.util.Generator;

import static com.lisong.learn.core.util.Print.print;

public class Exercise14 {

    private static class BasicGenerator<T> implements Generator<T> {
        private Class<T> type;
        BasicGenerator(Class<T> type) {
            this.type = type;
        }

        @Override
        public T next() {
            try {
                return type.newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        static <T> BasicGenerator<T> create(Class<T> type) {
            return new BasicGenerator<>(type);
        }
    }

    public static void main(String[] args) {

        Generator<CountedObject> gen = new BasicGenerator<>(CountedObject.class);
        for(int i = 0; i < 5; i++)
            print(gen.next());
    }
}

class CountedObject {

    private static long count = 0;
    private final long id = count++;
    @Override
    public String toString() {
        return "CountedObject :" + id;
    }
}