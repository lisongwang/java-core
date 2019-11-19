package com.lisong.learn.core.control;

/**
 * Fibonacci sequence.
 */
public class Exercise9 {

    static int f(int n) {

        if (n == 1)
            return 1;

        if (n == 2)
            return 1;

        return f(n - 1) + f(n - 2);
    }

    static void printFibonacci (int num) {

        int i = 1;
        while (f(i) <= num) {
            System.out.print(f(i) + ", ");
            i++;
        }
    }

    public static void main(String[] args) {

        int num = 10;

        //printFibonacci(5);

        for (int i = 1; i <= num; i++) {
            if (i == num)
                System.out.print(f(i));
            else
                System.out.print(f(i) + ", ");
        }
    }
}
