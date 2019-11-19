package com.lisong.learn.core.containers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.lisong.learn.core.util.Print.print;

public class Exercise6 {

    private static void unsupportedTest(String msg, List<String> list) {
        print("---------------" + msg + "---------------");
        List<String> list2 = new ArrayList<>(list.subList(2, 8));

        try {
            list.addAll(3, list2);
        } catch (Exception e) {
            print("addAll(index, collection): " + e);
        }

        try {
            list.add(3, "insert");
        } catch (Exception e) {
            print("add(index, object): " + e);
        }

        try {
            list.remove(3);
        } catch (Exception e) {
            print("remove(index): " + e);
        }

        try {
            list.set(0, "first");
        } catch (Exception e) {
            print("set(index, object): " + e);
        }

        try {
            list.replaceAll(String::toLowerCase);
        } catch (Exception e) {
            print("replaceAll(function): " + e);
        }
    }

    public static void main(String[] args) {

        List<String> list = Arrays.asList("A B C D E F G H I J K L M N".split(" "));
        unsupportedTest("Modifiable", new ArrayList<>(list));
        unsupportedTest("asList", list);
        unsupportedTest("Unmodifiable", Collections.unmodifiableList(list));
    }
}