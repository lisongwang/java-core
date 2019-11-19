package com.lisong.learn.core.arrays;

import com.lisong.learn.core.util.Generators;

import java.util.Arrays;

import static com.lisong.learn.core.util.Print.print;

public class Exercise18 {

    public static void main(String[] args) {

        BerylliumSphere[] spheres = Generators.fillArray(BerylliumSphere.class, BerylliumSphere::new , 5);
        print(Arrays.toString(spheres));
        BerylliumSphere[] dSpheres = new BerylliumSphere[spheres.length];
        print(Arrays.toString(dSpheres));
        System.arraycopy(spheres, 0, dSpheres, 0, spheres.length);
        print(Arrays.toString(dSpheres));
        print("spheres equals with dSpheres: " + Arrays.equals(spheres, dSpheres));
    }
}
