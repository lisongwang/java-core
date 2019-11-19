package com.lisong.learn.core.arrays;

import java.util.Arrays;

import static com.lisong.learn.core.util.Print.print;

/**
 * Combine exercise 5, exercise 6, exercise 7.
 */
public class Exercise7 {

    private static BerylliumSphere[][][] emptyThreeDimensionArray(int dimension1, int dimension2, int dimension3) {
        if(dimension1 < 1 || dimension2 < 1 || dimension3 < 1) {
            print("Array dimension must be > 0");
            return null;
        }
        BerylliumSphere[][][] emptyArray = new BerylliumSphere[dimension1][dimension2][dimension3];
        return emptyArray;
    }

    private static BerylliumSphere[][] twoDimensionArray(int dimension1, int dimension2) {

        if(dimension1 < 1 || dimension2 < 1) {
            print("Array dimension must be > 0");
            return null;
        }
        BerylliumSphere[][] bArray = new BerylliumSphere[dimension1][dimension2];
        for(int i = 0; i < dimension1; i++)
            for(int j = 0; j < dimension2; j++)
                bArray[i][j] = new BerylliumSphere();
        return bArray;
    }

    private static BerylliumSphere[][][] threeDimensionArray(int dimension1, int dimension2, int dimension3) {

        if(dimension1 < 1 || dimension2 < 1 || dimension3 < 1) {
            print("Array dimension must be > 0");
            return null;
        }
        BerylliumSphere[][][] bArray = new BerylliumSphere[dimension1][][];
        for(int i = 0; i < dimension1; i++)
            bArray[i] = twoDimensionArray(dimension2, dimension3);
        return bArray;
    }

    public static void main(String[] args) {
        print(Arrays.deepToString(twoDimensionArray(2, 3)));
        print(Arrays.deepToString(threeDimensionArray(2, 3, 4)));
        print(Arrays.deepToString(emptyThreeDimensionArray(2, 3, 4)));
    }
}