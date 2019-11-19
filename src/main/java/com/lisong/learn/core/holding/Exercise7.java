package com.lisong.learn.core.holding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.lisong.learn.core.util.Print.print;

public class Exercise7 {

    static class Element {
        private int i;
        Element(int i) {
            this.i = i;
        }

        @Override
        public String toString() {
            return "Element: " + i;
        }
    }

    public static void main(String[] args) {

        Element[] elements = new Element[10];
        for (int i = 0; i < elements.length; i++) {
            elements[i] = new Element(i);
        }

        List<Element> el = new ArrayList<>(Arrays.asList(elements));
        print(el);
        List<Element> sub = el.subList(2, 6);
        print(sub);
        Collections.shuffle(sub);
        print(sub);
        print(el);
        el.removeAll(sub);
        print(el);
    }
}
