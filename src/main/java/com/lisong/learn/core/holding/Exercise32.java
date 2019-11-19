package com.lisong.learn.core.holding;

import java.util.*;

import static com.lisong.learn.core.util.Print.print;

public class Exercise32 {

    public static void main(String[] args) {

        NonCollectionSequence gerbils = new NonCollectionSequence();
        for(Gerbil g : gerbils) {
            print(g);
        }
        print();
        for(Gerbil g : gerbils.reversed()) {
            print(g);
        }
        print();
        for(Gerbil g : gerbils.randomized()) {
            print(g);
        }
    }
}

class GerbilSequence {

    protected Random rand = new Random();
    protected Gerbil[] gerbils = new Gerbil[5];
    GerbilSequence() {
        for(int i = 0; i < gerbils.length; i++) {
            gerbils[i] = new Gerbil(rand.nextInt(100));
        }
    }
}

class NonCollectionSequence extends GerbilSequence implements Iterable<Gerbil> {

    @Override
    public Iterator<Gerbil> iterator() {
        return new Iterator<Gerbil>() {
            private int current = 0;
            @Override
            public boolean hasNext() {
                return current < gerbils.length;
            }

            @Override
            public Gerbil next() {
                return gerbils[current++];
            }
        };
    }

    public Iterable<Gerbil> reversed() {
        return new Iterable<Gerbil>() {
            @Override
            public Iterator<Gerbil> iterator() {
                return new Iterator<Gerbil>() {
                    private int current = gerbils.length - 1;
                    @Override
                    public boolean hasNext() {
                        return current > -1;
                    }

                    @Override
                    public Gerbil next() {
                        return gerbils[current--];
                    }
                };
            }
        };
    }

    public Iterable<Gerbil> randomized() {
        return new Iterable<Gerbil>() {
            @Override
            public Iterator<Gerbil> iterator() {
                List<Gerbil> shuffled = new ArrayList<>(Arrays.asList(gerbils));
                Collections.shuffle(shuffled, rand);
                return shuffled.iterator();
            }
        };
    }
}
