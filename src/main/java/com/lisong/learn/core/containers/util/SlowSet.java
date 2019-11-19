package com.lisong.learn.core.containers.util;

import java.util.*;

public class SlowSet<T> extends AbstractSet<T> {
    private List<T> elements = new ArrayList<>();
    private int modifyCount = 0;
    @Override
    public boolean add(T t) {
        if(!elements.contains(t)) {
            elements.add(t);
            modifyCount++;
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        if(super.remove(o)) {
            modifyCount++;
            return true;
        } else
            return false;
    }

    @Override
    public void clear() {
        super.clear();
        modifyCount++;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if(super.retainAll(c)) {
            modifyCount++;
            return true;
        } else
            return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int current = 0;
            private int lastReturn = -1;
            private int modifyInIterator = modifyCount;

            @Override
            public boolean hasNext() {
                return current < elements.size();
            }

            @Override
            public T next() {
                checkForModification();
                if(current >= elements.size())
                    throw new ConcurrentModificationException();
                lastReturn = current;
                current++;
                return elements.get(lastReturn);
            }

            @Override
            public void remove() {
                if(lastReturn == -1)
                    throw new IllegalStateException();
                checkForModification();
                try {
                    elements.remove(lastReturn);
                    current = lastReturn;
                    lastReturn = -1;
                    modifyInIterator = modifyCount;
                }catch(IndexOutOfBoundsException e) {
                    throw new ConcurrentModificationException();
                }
            }

            private void checkForModification() {
                if(modifyCount != modifyInIterator)
                    throw new ConcurrentModificationException();
            }
        };
        //return elements.iterator();
    }

    @Override
    public int size() {
        return elements.size();
    }

    public static void main(String[] args) {
        Set<String> set = new SlowSet<>();
        set.add("a");
        set.add("b");
        Iterator<String> it = set.iterator();
        set.remove("b");
        while(it.hasNext())
            it.next();
    }
}