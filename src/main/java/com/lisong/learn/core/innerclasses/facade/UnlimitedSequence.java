package com.lisong.learn.core.innerclasses.facade;

import java.util.ArrayList;
import java.util.List;

public class UnlimitedSequence<T> extends Sequence<T> {

    private List<T> itemList = new ArrayList<>();

    public UnlimitedSequence() {
        super(0);
    }

    public UnlimitedSequence(int size) {
        super(0);
    }

    @Override
    public void addItem(T item) {
        itemList.add(item);
    }

    private class UnlimitedSequenceSelector implements Selector<T> {

        private int current = 0;

        @Override
        public boolean end() {
            return current >= itemList.size();
        }

        @Override
        public T current() {
            return itemList.get(current);
        }

        @Override
        public void next() {
            current ++;
        }
    }

    private class UnlimitedSequenceInverseSelector implements Selector<T> {

        private int current = itemList.size() - 1;

        @Override
        public boolean end() {
            return current < 0;
        }

        @Override
        public T current() {
            return itemList.get(current);
        }

        @Override
        public void next() {
            current--;
        }
    }

    @Override
    public Selector<T> getSelector() {
        return new UnlimitedSequenceSelector();
    }

    @Override
    public Selector<T> getInverseSelector() {
        return new UnlimitedSequenceInverseSelector();
    }
}
