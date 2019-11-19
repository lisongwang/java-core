package com.lisong.learn.core.generics;

import com.lisong.learn.core.generics.coffee.CoffeeGenerator;
import com.lisong.learn.core.generics.movie.StoryCharacterGenerator;
import com.lisong.learn.core.util.Generators;

import java.util.*;

import static com.lisong.learn.core.util.Print.print;

public class Exercise13 {



    public static void main(String[] args) {

        Collection c = Generators.fill(new ArrayList<>(), new CoffeeGenerator(), 5);
        print("collection type: " + c.getClass().getSimpleName());
        print(c);
        c = Generators.fill(new LinkedList<>(), new CoffeeGenerator(), 5);
        print("collection type: " +  c.getClass().getSimpleName());
        print(c);
        c = Generators.fill(new ArrayDeque<>(), new CoffeeGenerator(), 5);
        print("collection type: " + c.getClass().getSimpleName());
        print(c);
        c = Generators.fill(new HashSet<>(), new StoryCharacterGenerator(), 5);
        print("collection type: " +  c.getClass().getSimpleName());
        print(c);
        c = Generators.fill(new LinkedList<>(), new StoryCharacterGenerator(), 5);
        print("collection type: " +  c.getClass().getSimpleName());
        print(c);
    }
}
