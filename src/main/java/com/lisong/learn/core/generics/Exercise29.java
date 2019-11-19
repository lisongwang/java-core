package com.lisong.learn.core.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.lisong.learn.core.util.Print.print;

public class Exercise29 {

    static void f1(Holder<List<?>> holder) {

        List l = holder.getObj();
        print("Holder: " + holder);
        print("List<?> type: " + l.getClass().getTypeName());
        Collections.addAll(l, "a",'b',10, 20.2);
        print("size() " + l.size());
        print("get(index:0) " + l.get(0));
        print("remove(index:1) " + l.remove(1));
        print("remove(object:10) " + l.remove(Integer.valueOf(10)));
        print(l.contains('b'));
        l.clear();
        print("After clear size: " + l.size());
        Collections.addAll(l, 1, 2, 3, 4);
        List<?> ls = new ArrayList<>(l);
        print("l.equal(ls) " + ls.equals(l));
        holder.setObj(ls);
        List<?> l1 = holder.getObj();
        print(holder.equals(l1));
        print("List<?> type: " + l1.getClass().getTypeName());
        print(l1.size());
        print(l1);
        print(l1.remove(1));
        //l1.add(2);
        l1.remove(Integer.valueOf(4));
        //l1.set(1, Integer.valueOf(2));
        l1.retainAll(Arrays.asList(1, 2));
        print(l1);
        l1.addAll(l);
        print("l1 contains 3: " + l1.contains(3));
        Integer[] ints = l1.toArray(new Integer[]{});
        print(Arrays.toString(ints));
        print(holder.equals(ints));
        print(holder);
    }
    static void f2(List<Holder<?>> list) {

        list.add(new Holder<>("a"));
        list.add(new Holder<>('b'));
        list.add(new Holder<>(10));
        list.add(new Holder<>(10.2));
        print(list);
        list.remove(1);
        list.set(2, new Holder<>('c'));
        //list.addAll(Arrays.asList(new Holder(1), new Holder("ss")));
        print(list);
        Holder<?> h = list.get(1);
        //h.setObj(1);
        print("holder.getObj type: " + h.getObj().getClass().getSimpleName());
    }

    public static void main(String[] args) {
        Holder<List<?>> holder = new Holder<>(new ArrayList<String>());
        f1(holder);
        print();
        List<Holder<?>> list = new ArrayList<>();
        f2(list);
    }
}