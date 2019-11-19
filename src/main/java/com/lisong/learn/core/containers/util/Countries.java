package com.lisong.learn.core.containers.util;

import java.util.*;

import static com.lisong.learn.core.util.Print.print;

public class Countries {

    public static final String[][] DATA = {
            {"ALGERIA","Algiers"},{"ANGOLA","Luanda"},{"Egypt","Cairo"},{"Morocco","Rabat"},
            {"CAMEROON","Yaounde"},{"ZAMBIA","Lusaka"},{"South Africa","Pretoria"},{"NIGERIA","Abuja"},
            {"CHINA","Beijing"},{"INDIA","New Delhi"},{"INDONESIA","Jakarta"},{"JAPAN","Tokyo"},
            {"THAILAND","Bangkok"},{"SOUTH KOREA","Seoul"},{"TURKEY","Ankara"},{"IRAQ","Baghdad"},
            {"AUSTRALIA","Canberra"},{"NAURU","Yaren"},{"FIJI","Suva"},{"WESTERN SAMOA","Apia"},
            {"ARMENIA","Yerevan"},{"AZERBAIJAN","Baku"},{"GEORGIA","Tbilisi"},{"RUSSIA","Moscow"},
            {"AUSTRIA","Vienna"},{"BELGIUM","Brussels"},{"CZECH REPUBLIC","Prague"},{"DENMARK","Copenhagen"},
            {"FINLAND","Helsinki"},{"FRANCE","Paris"},{"HUNGARY","Budapest"},{"IRELAND","Dublin"},
            {"ITALY","Roma"},{"MONACO","Monaco"},{"THE NETHERLANDS","Amsterdam"},{"NORWAY","Oslo"},
            {"POLAND","Warsaw"},{"PORTUGAL","Lisbon"},{"SPAIN","Madrid"},{"UNITED KINGDOM","London"},
            {"GERMANY","Berlin"},{"GREECE","Athens"},{"SWEDEN","Stockholm"},{"SWITZERLAND","Bern"},
            {"CANADA","Ottawa"},{"MEXICO","Mexico City"},{"PANAMA","Panama City"},{"UNITED STATES OF AMERICA","Washington, D.C."},
            {"BRAZIL","Brasilia"},{"CHILE","Santiago"},{"COLOMBIA","Bogota"},{"ARGENTINA","BA"},};

    private static class FlyweightMap extends AbstractMap<String, String> {

        private static class Entry implements Map.Entry<String,String> {
            private int index;

            private Entry(int index) {
                this.index = index;
            }

            @Override
            public String getKey() {
                return DATA[index][0];
            }

            @Override
            public String getValue() {
                return DATA[index][1];
            }

            @Override
            public int hashCode() {
                return DATA[index][0].hashCode();
            }

            @Override
            public boolean equals(Object obj) {
                return DATA[index][0].equals(obj);
            }

            @Override
            public String setValue(String value) {
                throw new UnsupportedOperationException();
            }
        }

        private static class EntrySet extends AbstractSet<Map.Entry<String,String>> {
            private int size;

            private EntrySet(int size) {
                this.size = size <= 0 ? 0 : (size > DATA.length ? DATA.length : size);
            }

            @Override
            public Iterator<Map.Entry<String,String>> iterator() {
                return new Iterator<Map.Entry<String,String>>() {
                    private Entry entry = new Entry(-1);
                    @Override
                    public boolean hasNext() {
                        return entry.index < size - 1;
                    }

                    @Override
                    public Map.Entry<String,String> next() {
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

        private static EntrySet entries = new EntrySet(DATA.length);

        @Override
        public Set<Map.Entry<String, String>> entrySet() {
            return entries;
        }
    }

    private static Map<String,String> select(final int init) {
        return new FlyweightMap() {
            @Override
            public Set<Map.Entry<String, String>> entrySet() {
                return new FlyweightMap.EntrySet(init);
            }
        };
    }

    private static Map<String,String> map = new FlyweightMap();

    public static Map<String,String> capitals() {
        return map;
    }

    public static Map<String,String> capitals(int init) {
        return select(init);
    }

    public static Set<String> names() {
        return map.keySet();
    }

    public static Set<String> names(int init) {
        return select(init).keySet();
    }

    public static void main(String[] args) {

        print(capitals(10));
        print(names(10));
        print(new HashMap<>(capitals(3)));
        print(new LinkedHashMap<>(capitals(3)));
        print(new TreeMap<>(capitals(3)));
        print(new HashSet<>(names(6)));
        print(new LinkedHashSet<>(names(6)));
        print(new TreeSet<>(names(6)));
        print(new ArrayList<>(names(6)));
        print(new LinkedList<>(names(6)));
        print(capitals().get("CHINA"));
    }
}