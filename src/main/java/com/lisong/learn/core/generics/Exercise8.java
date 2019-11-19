package com.lisong.learn.core.generics;

import com.lisong.learn.core.generics.movie.BadGuys;
import com.lisong.learn.core.generics.movie.GoodGuys;
import com.lisong.learn.core.generics.movie.StoryCharacterGenerator;
import com.lisong.learn.core.generics.movie.StoryCharacters;

import static com.lisong.learn.core.util.Print.printnb;

public class Exercise8 {

    public static void main(String[] args) {

        StoryCharacterGenerator sg = new StoryCharacterGenerator();
        for(int i = 0; i < 10; i++) {
            StoryCharacters s = sg.next();
            if(s instanceof GoodGuys)
                printnb( s + "(GoodGuys) ");
            else if(s instanceof BadGuys)
                printnb( s + "(BadGuys) ");
        }
    }
}
