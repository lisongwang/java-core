package com.lisong.learn.core.enumerated;

import com.lisong.learn.core.enumerated.enums.Course;

import static com.lisong.learn.core.util.Print.print;

/**
 * Combine exercise 3, exercise 4.
 */
public class Exercise4 {

    public static void main(String[] args) {
        for(int i = 0; i < 5; i++) {
            for(Course c : Course.values()) {
                print(c.randomSelect());
            }
            print("----------");
        }
    }
}