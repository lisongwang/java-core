package com.lisong.learn.core.arrays;

import java.util.ArrayList;
import java.util.List;

import static com.lisong.learn.core.util.Print.print;

public class Exercise10 {

    public static void main(String[] args) {

        List<List<String>> ls = new ArrayList<>();
        List<List> ll = new ArrayList<>();
        //ls = (List<List<String>>)ll;
        ls.add(new ArrayList<>());
        //ls.add(new ArrayList<Integer>());
        List<List> la = (List)ls;
        la.add(new ArrayList<Integer>()); // still can misuse the container.
        la.get(0).add(10);
        la.get(0).add("String");
        print("la.get(0).get(0) " + la.get(0).get(0));
        print("la.get(0).get(1) " + la.get(0).get(1));

        List<List<BerylliumSphere>> beryll = new ArrayList<>();
        for(int i = 0; i < 10; i++)
            beryll.add(new ArrayList<>());
        beryll.get(0).add(new BerylliumSphere());
        beryll.get(0).add(new BerylliumSphere());
        beryll.get(5).add(new BerylliumSphere());
        //beryll.get(0).add(10);
        print(beryll);
    }
}
