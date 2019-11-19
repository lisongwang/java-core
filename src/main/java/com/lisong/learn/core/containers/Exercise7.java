package com.lisong.learn.core.containers;

import com.lisong.learn.core.containers.util.Countries;

import java.util.*;

import static com.lisong.learn.core.util.Print.print;
import static com.lisong.learn.core.util.Print.printnb;

public class Exercise7 {

    public static void main(String[] args) {

        List<String> list1 = new ArrayList<>(Countries.names(10)).subList(0,5);
        List<String> list2 = new LinkedList<>(Countries.names(10)).subList(5,10);
        Iterator<String> it1 = list1.iterator();
        Iterator<String> it2 = list2.iterator();
        while(it1.hasNext()) {
            printnb(it1.next() + (it1.hasNext() ? ", " : ""));
        }
        print();
        while(it2.hasNext()) {
            printnb(it2.next() + (it2.hasNext() ? ", " : ""));
        }
        print();

        //insert list2 at every pos of list1
        ListIterator<String> it3 = list1.listIterator();
        ListIterator<String> it4 = list2.listIterator();
        while(it4.hasNext()) {
            it3.add(it4.next());
            it3.next();
        }
        print("after insert list1: " + list1);

        while(it4.hasPrevious())
            it4.previous();

        List<String> list3 = new ArrayList<>(Countries.names(5));
        ListIterator<String> it5 = list3.listIterator();
        while(it5.hasNext())
            it5.next();

        while(it4.hasNext()) {
            it5.add(it4.next());
            it5.previous();
            it5.previous();
        }
        print("after insert from backend list1: " + list3);
    }
}