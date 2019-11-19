package com.lisong.learn.core.util;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class Sets {

    public static <T> Set<T> union(Set<T> set1, Set<T> set2) {
        Set<T> result = null;
        if(set1 instanceof EnumSet) {
            result = ((EnumSet) set1).clone();
        }else {
            result = new HashSet<>(set1);
        }
        result.addAll(set2);
        return result;
    }

    public static <T> Set<T> intersection(Set<T> set1, Set<T> set2) {
        Set<T> result = null;
        if(set1 instanceof EnumSet) {
            result = ((EnumSet) set1).clone();
        }else {
            result = new HashSet<>(set1);
        }
        result.retainAll(set2);
        return result;
    }

    public static <T> Set<T> difference(Set<T> superset, Set<T> subset) {
        Set<T> result = null;
        if(superset instanceof EnumSet) {
            result = ((EnumSet) superset).clone();
        }else {
            result = new HashSet<>(superset);
        }
        result.removeAll(subset);
        return result;
    }

    public static <T> Set<T> complement(Set<T> set1, Set<T> set2) {
        return difference(union(set1, set2), intersection(set1, set2));
    }
}
