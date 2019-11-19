package com.lisong.learn.core.containers.test;

import com.lisong.learn.core.containers.util.CollectionData;
import com.lisong.learn.core.util.Generator;

import java.util.*;

public class ListPerformance<T> {

    private static Random rand = new Random(100);
    private static int reps = 100;
    public List<Test<List<T>>> list = new ArrayList<>();
    public List<Test<LinkedList<T>>> qlist = new ArrayList<>();
    public List<Test<List<T>>> slist = new ArrayList<>();
    private Generator<T> gen;
    private T t;
    private Comparator<T> comparator;

    public ListPerformance(Generator<T> gen, Comparator<T> cmp) {
        this.gen = gen;
        t = gen.next();
        comparator = cmp;
        init();
    }

    private void init() {
        list.add(new Test<List<T>>("add") {
            @Override
            public long test(List<T> container, TestParam param) {
                int loop = param.loop;
                int size = param.size;
                for(int i = 0; i < loop; i++) {
                    container.clear();
                    for(int j = 0; j < size; j++)
                        container.add(t);
                }
                return loop*size;
            }
        });

        list.add(new Test<List<T>>("get") {
            @Override
            public long test(List<T> container, TestParam param) {
                int loop = param.loop*reps;
                int size = param.size;
                for(int i = 0; i < loop; i++)
                    container.get(rand.nextInt(size));
                return loop;
            }
        });

        list.add(new Test<List<T>>("set") {
            @Override
            public long test(List<T> container, TestParam param) {
                int loop = param.loop*reps;
                int size = param.size;
                for(int i = 0; i < loop; i++)
                    container.set(rand.nextInt(size), t);
                return loop;
            }
        });

        list.add(new Test<List<T>>("iteradd") {
            @Override
            public long test(List<T> container, TestParam param) {
                final int loop = 1000000;
                int half = param.size/2;
                ListIterator<T> it = container.listIterator(half);
                for(int i = 0; i < loop; i++)
                    it.add(t);
                return loop;
            }
        });

        list.add(new Test<List<T>>("insert") {
            @Override
            public long test(List<T> container, TestParam param) {
                int loop = param.loop*reps;
                for(int i = 0; i < loop; i++)
                    container.add(5,t);
                return loop;
            }
        });

        list.add(new Test<List<T>>("remove") {
            @Override
            public long test(List<T> container, TestParam param) {
                int loop = param.loop;
                int size = param.size;
                for(int i = 0; i < loop; i++) {
                    container.clear();
                    container.addAll(new CollectionData<>(gen, size));
                    while(container.size() > 5)
                        container.remove(5);
                }
                return loop*size;
            }
        });

        //test for queue
        qlist.add(new Test<LinkedList<T>>("addFirst") {
            @Override
            public long test(LinkedList<T> container, TestParam param) {
                int loop = param.loop;
                int size = param.size;
                for(int i = 0; i < loop; i++) {
                    container.clear();
                    for(int j = 0; j < size; j++)
                        container.addFirst(t);
                }
                return loop*size;
            }
        });

        qlist.add(new Test<LinkedList<T>>("addLast") {
            @Override
            public long test(LinkedList<T> container, TestParam param) {
                int loop = param.loop;
                int size = param.size;
                for(int i = 0; i < loop; i++) {
                    container.clear();
                    for(int j = 0; j < size; j++)
                        container.addLast(t);
                }
                return loop*size;
            }
        });

        qlist.add(new Test<LinkedList<T>>("rmFirst") {
            @Override
            public long test(LinkedList<T> container, TestParam param) {
                int loop = param.loop;
                int size = param.size;
                for(int i = 0; i < loop; i++) {
                    container.clear();
                    container.addAll(new CollectionData<>(gen, size));
                    while(container.size() > 0)
                        container.removeFirst();
                }
                return loop*size;
            }
        });

        qlist.add(new Test<LinkedList<T>>("rmLast") {
            @Override
            public long test(LinkedList<T> container, TestParam param) {
                int loop = param.loop;
                int size = param.size;
                for(int i = 0; i < loop; i++) {
                    container.clear();
                    container.addAll(new CollectionData<>(gen, size));
                    while(container.size() > 0)
                        container.removeLast();
                }
                return loop*size;
            }
        });

        slist.add(new Test<List<T>>("sort") {
            @Override
            public long test(List<T> container, TestParam param) {
                int loop = param.loop;
                int size = param.size;
                for(int i = 0; i < loop; i++) {
                    container.clear();
                    container.addAll(new CollectionData<>(gen, size));
                    Collections.sort(container, comparator);
                }
                return loop;
            }
        });
    }

    public class ListTester<T> extends Tester<List<T>> {
        @Override
        protected List<T> initialize(int size) {
            container.clear();
            container.addAll(new CollectionData(gen, size));
            return container;
        }

        public ListTester(List<T> container, List<Test<List<T>>> tests) {
            super(container, tests);
        }
    }
}