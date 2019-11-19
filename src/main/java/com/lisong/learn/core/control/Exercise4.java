package com.lisong.learn.core.control;

/**
 * Print for prime numbers.
 */
public class Exercise4 {

    static void printPrime(int num) {

        int total = 0;
        if (num >= 2) {
            System.out.println("2 is a prime number!");
            total++;
            for (int i = 3; i <= num; i+=2) {

                boolean divided = false;
                for (int j = 3; j <= Math.sqrt(i); j+=2) {
                    if (i%j == 0) {
                        divided = true;
                        break;
                    }
                }
                if (!divided) {
                    System.out.println(i + " is a prime number!");
                    total++;
                }
            }
        }

        System.out.println("Total prime numbers within " + num + " is: " + total);
    }

    public static void main(String[] args) {

        printPrime(100);
    }
}
