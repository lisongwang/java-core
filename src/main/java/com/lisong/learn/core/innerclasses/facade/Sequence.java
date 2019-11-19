package com.lisong.learn.core.innerclasses.facade;

import java.util.Iterator;

public class Sequence<T> {

    private Object[] items;
    private int next = 0;

    public Sequence(int size) {
        items = new Object[size];
    }

    public void addItem(T item) {
        if(next < items.length)
            items[next++] = item;
        else
            throw new IndexOutOfBoundsException();
    }

    public void addItemWithException(T item) throws SequenceOverflowException {
        if(next < items.length)
            items[next++] = item;
        else
            throw new SequenceOverflowException();
    }

    private class SequenceSelector implements Selector<T> {

        private int current = 0;

        @Override
        public boolean end() {
            return current >= items.length;
        }

        @Override
        @SuppressWarnings("unchecked")
        public T current() {
            if (current < items.length)
                return (T)items[current];
            return null;
        }

        @Override
        public void next() {
            current++;
        }
    }


    private class InverseSelector implements Selector<T> {

        private int current = next - 1;
        @Override
        public boolean end() {
            return current < 0;
        }

        @Override
        @SuppressWarnings("unchecked")
        public T current() {
            if (current >= 0)
                return (T)items[current];
            return null;
        }

        @Override
        public void next() {
            current--;
        }
    }

    public Selector<T> getInverseSelector () { return new InverseSelector(); }

    public Selector<T> getSelector() {
        return new SequenceSelector();
    }

    public Iterator<T> iterator() {

        return new Iterator<T>() {

            private int current = 0;

            @Override
            public boolean hasNext() {
                return current < items.length;
            }

            @Override
            @SuppressWarnings("unchecked")
            public T next() {
                return (T)items[current++];
            }
        };
    }
}
