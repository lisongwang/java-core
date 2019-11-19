package com.lisong.learn.core.holding;

import java.util.*;

import static com.lisong.learn.core.util.Print.print;

/**
 * Combine exercise 10, exercise 11.
 */
public class Exercise11 {

    static void printContainer(Iterator it) {

        while(it.hasNext())
            print(it.next());
        print();
    }

    public static void main(String[] args) {

        Collection c = new ArrayList();
        c.add("Object 1");
        c.add("Object 2");
        c.add("Object 3");
        c.add("Object 4");
        c.add("Object 5");
        printContainer(c.iterator());

        c = new LinkedList(Arrays.asList(1, 3, 5, 7, 9));
        printContainer(c.iterator());

        c = new HashSet(Arrays.asList('a', 'b', 'd', 'c', 'a'));
        printContainer(c.iterator());
        c = new TreeSet(Arrays.asList('a', 'b', 'd', 'c', 'a'));
        printContainer(c.iterator());
        c = new LinkedHashSet(Arrays.asList('a', 'b', 'd', 'c', 'a'));
        printContainer(c.iterator());

        c = new PriorityQueue(Arrays.asList(12.1, 11.2, 11.2, 8.3, 9.2));
        printContainer(c.iterator());
    }
}
