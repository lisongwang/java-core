package com.lisong.learn.core.containers;

import com.lisong.learn.core.containers.util.Countries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static com.lisong.learn.core.util.Print.print;

public class Exercise1 {

    public static void main(String[] args) {

        List<String> countries = new ArrayList<>(Countries.names());
        List<String> capitals = new LinkedList<>(Countries.capitals().values());
        Collections.sort(countries);
        Collections.sort(capitals);
        print("sorted countries: " + countries);
        print("sorted capitals: " + capitals);

        for(int i = 0; i < 3; i++) {
            Collections.shuffle(countries);
            Collections.shuffle(capitals);
            print("countries after shuffle: " + i + " " + countries);
            print("capitals after shuffle: " + i + " " + capitals);
        }

        countries = new ArrayList<>(Countries.names(15));
        capitals = new LinkedList<>(Countries.capitals(15).values());
        for(int i = 0; i < 5; i++) {
            Collections.shuffle(countries);
            Collections.shuffle(capitals);
            print("countries after shuffle: " + i + " " + countries);
            print("capitals after shuffle: " + i + " " + capitals);
            Collections.sort(countries);
            Collections.sort(capitals);
            print("sorted countries: " + countries);
            print("sorted capitals: " + capitals);
        }
    }
}