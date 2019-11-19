package com.lisong.learn.core.containers.util;

import com.lisong.learn.core.util.Generators;

import java.util.*;

import static com.lisong.learn.core.util.Print.print;

public class TupleString implements Comparable<TupleString> {

    final String first;
    final String second;

    public TupleString(String first, String second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public int compareTo(TupleString o) { return String.CASE_INSENSITIVE_ORDER.compare(first, o.first); }

    @Override
    public int hashCode() {
        int result = 17;
        result = 37*result + (first == null ? 0 : first.hashCode());
        result = 37*result + (second == null ? 0 : second.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (hashCode() != obj.hashCode())
            return false;
        if (this.getClass() == obj.getClass()) {
            TupleString ts = (TupleString)obj;
            return Objects.equals(first, ts.first) && Objects.equals(second, ts.second);
        }
        return false;
    }

    public String getFirst() {
        return first;
    }

    public String getSecond() {
        return second;
    }

    @Override
    public String toString() { return first + "=" + second; }

    public static void testSort(Comparator<TupleString> comp) {
        TupleString[] tsArray = Generators.fillArray(TupleString.class,
                ()->new TupleString(MapData.randStrGen.next(),MapData.randStrGen.next()), 7);
        List<TupleString> tsList = Generators.fill(new ArrayList<>(),
                ()->new TupleString(MapData.randStrGen.next(),MapData.randStrGen.next()), 7);
        print("tsArray: " + Arrays.toString(tsArray));
        print("tsList: " + tsList);
        Arrays.sort(tsArray);
        Collections.sort(tsList);
        print("tsArray after first sort: " + Arrays.toString(tsArray));
        print("tsList after first sort: " + tsList);
        Arrays.sort(tsArray, comp);
        tsList.sort(comp);
        print("tsArray after second sort: " + Arrays.toString(tsArray));
        print("tsList after second sort: " + tsList);
        TupleString key1 = tsArray[3];
        int i = Arrays.binarySearch(tsArray, key1, comp);
        print("Location of " + key1 + " is index " + i + ", array[i]: " + tsArray[i]);
        TupleString key2 = tsList.get(5);
        int j = Collections.binarySearch(tsList,key2,comp);
        print("Location of " + key2 + " is index " + j + ", list.get(j): " + tsList.get(j));
    }
}