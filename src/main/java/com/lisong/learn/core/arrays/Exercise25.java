package com.lisong.learn.core.arrays;

import java.util.*;

import static com.lisong.learn.core.util.Print.print;

public class Exercise25 {

    static class MyList<T> extends ArrayList<T> {
        MyList(Collection<? extends T> c) {
            super(c);
        }

        List<T> getReversed() {
            List<T> list = new ArrayList<T>();
            ListIterator<T> it = listIterator(size());
            while(it.hasPrevious())
                list.add(it.previous());
            return list;
        }
    }

    public static void main(String[] args) {

        List<Integer> aList = new ArrayList<>();
        Collections.addAll(aList, 1,2,3,4,5);
        print("type aList: " + aList.getClass());
        print("aList: " + aList);
        print("aList[4]: " + aList.get(4));
        aList.add(6);
        aList.addAll(Arrays.asList(7,8));
        print("aList: " + aList);
        List<Integer> aSlice = aList.subList(2,4);
        print("aSlice: " + aSlice);
        MyList<Integer> list2 = new MyList<>(aList);
        print("type myList: " + list2.getClass());
        print("list2.getReversed() " + list2.getReversed());
    }
}