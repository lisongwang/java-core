package com.lisong.learn.core.containers;

import com.lisong.learn.core.containers.util.SList;
import com.lisong.learn.core.containers.util.SListIterator;

import static com.lisong.learn.core.util.Print.print;

public class Exercise8 {

    public static void main(String[] args) {

        SList<Integer> ilist = new SList<>();
        SListIterator<Integer> it1 = ilist.iterator();
        for(int i = 0; i < 5; i++)
            it1.add(i);
        print(ilist);

        SList<String> slist = new SList<>();
        SListIterator<String> it2 = slist.iterator();
        it2.add("element1");
        it2.add("element2");
        it2.add("element3");
        it2.add("element4");
        it2.add("element5");
        print(slist);
        it2.init();
        it2.remove();
        it2.remove();
        it2.add("element6");
        it2.add("element7");
        it2.remove();
        it2.remove();
        it2.remove();
        print(slist);
    }
}