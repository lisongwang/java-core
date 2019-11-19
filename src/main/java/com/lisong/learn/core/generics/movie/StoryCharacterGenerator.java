package com.lisong.learn.core.generics.movie;

import com.lisong.learn.core.util.Generator;

import java.util.*;

public class StoryCharacterGenerator implements Generator<StoryCharacters>, Iterable<StoryCharacters> {

    private static final List<Class<? extends StoryCharacters>> types = new ArrayList<>();
    static {
        Collections.addAll(types,
                IronMan.class,
                CaptainAmerica.class,
                Loki.class,
                Thanos.class);
    }
    private int size = 0;
    public StoryCharacterGenerator() {}
    public StoryCharacterGenerator(int size) { this.size = size; }
    private static Random rand = new Random(788);
    @Override
    public StoryCharacters next() {
        try {
            return types.get(rand.nextInt(types.size())).newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Iterator<StoryCharacters> iterator() {
        return new Iterator<StoryCharacters>() {
            private int count = size;
            @Override
            public boolean hasNext() {
                return count > 0;
            }

            @Override
            public StoryCharacters next() {
                count--;
                return StoryCharacterGenerator.this.next();
            }
        };
    }
}
