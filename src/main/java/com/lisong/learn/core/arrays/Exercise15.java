package com.lisong.learn.core.arrays;

import com.lisong.learn.core.util.Generator;
import com.lisong.learn.core.util.Generators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.lisong.learn.core.util.Print.print;

public class Exercise15 {

    private static Generator<BerylliumSphere> beryGen = BerylliumSphere::new;

    public static void main(String[] args) {

        BerylliumSphere[] spheres = Generators.fillArray(BerylliumSphere.class, beryGen, 5);
        print(Arrays.toString(spheres));
        print(spheres[4]);
        List<BerylliumSphere> sphereList = new ArrayList<>();
        for(int i = 0; i < 5; i++)
            sphereList.add(beryGen.next());
        print(sphereList);
        print(sphereList.get(4));
    }
}
