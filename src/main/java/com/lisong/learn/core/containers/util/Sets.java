package com.lisong.learn.core.containers.util;

import com.lisong.learn.core.util.CountingGenerator;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static com.lisong.learn.core.util.Print.print;

public class Sets {

    public static void testSet(Set<String> set) {

        List<String> list1 = new CollectionData<>(new CountingGenerator.String(5), 10);
        List<String> list2 = new CollectionData<>(new CountingGenerator.String(5), 5);
        set.addAll(list1);
        set.addAll(list1);
        print(set);
        set.retainAll(list2);
        print("containsAll? " + set.containsAll(list2));
        set.removeAll(list2);
        print("isEmpty? " + set.isEmpty());
        set.addAll(list1);
        Iterator<String> it = set.iterator();
        while(it.hasNext()) {
            it.next();
            it.remove();
            //set.remove(it.next());
        }
        print("size(): " + set.size());
    }
}