package com.lisong.learn.core.enumerated.enums;

public enum LowChar {
    VOWEL("vowel", 'a','e','i','o','u'),
    SOMETIMES_A_VOWEL("Sometimes a vowel",'w','y'),
    CONSONANT("consonant",'b','c','d','f','g','h','j','k','l','m','n','p','q','r','s','t','v','x','z');

    private int[] chars;
    private String description;
    LowChar(String description, int... chars) {
        this.description = description;
        this.chars = chars;
    }

    public boolean contains(int c) {
        for(int i : chars) {
            if(i == c)
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return description;
    }
}