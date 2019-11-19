package com.lisong.learn.core.containers;

import java.util.*;

import static com.lisong.learn.core.util.Print.print;

public class Exercise5 {

    public static void main(String[] args) {
        print(new CountingMapData(60));
    }
}

class CountingMapData extends AbstractMap<Integer,String> {
    private static final String[] chars =
            "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z".split(" ");
    private int size;

    public CountingMapData(int size) {
        this.size = size < 0 ? 0 : size;
    }

    private static class Entry implements Map.Entry<Integer,String> {
        private int index;

        public Entry(int index) {
            this.index = index;
        }

        @Override
        public Integer getKey() {
            return Integer.valueOf(index);
        }

        @Override
        public String getValue() {
            return chars[index%chars.length]+(index/chars.length);
        }

        @Override
        public String setValue(String value) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean equals(Object o) {
            return Integer.valueOf(index).equals(o);
        }

        @Override
        public int hashCode() {
            return Integer.valueOf(index).hashCode();
        }
    }

    private static class EntrySet extends AbstractSet<Map.Entry<Integer,String>> {
        private int size;

        public EntrySet(int size) {
            this.size = size;
        }

        @Override
        public Iterator<Map.Entry<Integer,String>> iterator() {
            return new Iterator<Map.Entry<Integer,String>>() {
                private Entry entry = new Entry(-1);
                @Override
                public boolean hasNext() {
                    return entry.index < size - 1;
                }

                @Override
                public Map.Entry<Integer, String> next() {
                    entry.index++;
                    return entry;
                }
            };
        }

        @Override
        public int size() {
            return size;
        }
    }


    @Override
    public Set<Map.Entry<Integer,String>> entrySet() {
        return new EntrySet(size);
    }
}