package com.lisong.learn.core.arrays;

import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

import static com.lisong.learn.core.util.Print.print;

/**
 * Combine exercise 3, exercise 4.
 */
public class Exercise4 {

    private static Double[][] doubleArray(int dimension1, int dimension2, double min, double max) {

        if(dimension1 < 1 || dimension2 < 1) {
            print("Array dimension must be > 0");
            return null;
        }
        if(min >= max) {
            print("Min value must less than Max value");
            return null;
        }

        Random rand = new Random(669);
        Double[][] dArray = new Double[dimension1][dimension2];
        for(int i = 0; i < dimension1; i++) {
            for(int j = 0; j < dimension2; j++) {
                dArray[i][j] = min + (max-min)*rand.nextDouble();
            }
        }
        return dArray;
    }

    private static Double[][][] doubleArray(int dimension1, int dimension2, int dimension3, double min, double max) {

        if(dimension1 < 1 || dimension2 < 1 || dimension3 < 1) {
            print("Array dimension must be > 0");
            return null;
        }
        if(min >= max) {
            print("Min value must less than Max value");
            return null;
        }

        Double[][][] dArray = new Double[dimension1][][];
        for(int i = 0; i < dimension1; i++)
            dArray[i] = doubleArray(dimension2, dimension3, min, max);
        return dArray;
    }

    static void printArray(Double[][] dArray) {
        print(Arrays.deepToString(Optional.ofNullable(dArray).orElse(new Double[][]{})));
    }

    static void printArray(Double[][][] dArray) {
        print(Arrays.deepToString(Optional.ofNullable(dArray).orElse(new Double[][][]{})));
    }

    public static void main(String[] args) {
        printArray(doubleArray(0, 2, 4, 5));
        printArray(doubleArray(2, 3, 5, 4));
        printArray(doubleArray(1, 2, 5, 10));
        printArray(doubleArray(2, 2, 3, 5, 10));
        printArray(doubleArray(3, 3, 4,5, 10));
        printArray(doubleArray(3, 5, 1, 10, 20));
    }
}
