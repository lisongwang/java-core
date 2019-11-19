package com.lisong.learn.core.control;


import static com.lisong.learn.core.util.Print.print;

/**
 * Show the use of break and return.
 * Show the label for.
 */
public class Exercise7 {

    static void printInt () {

        for (int i = 1; i <= 100; i++) {
            if (i == 99)
                return;
            System.out.print(i + " ");
        }
    }

    static void labelFor () {

        int i = 0;
        outer:
        for (; true; ) {

            inner:
            for (; i < 10; i++) {

                if (i == 2) {
                    print("continue");
                    continue;
                }

                if (i == 3) {
                    print("break");
                    i++;
                    break;
                }

                if (i == 7) {
                    print("continue outer");
                    i++;
                    continue outer;
                }

                if (i == 8) {
                    print("break outer");
                    break outer;
                }

                for (int k = 0; k < 10; k++) {
                    if (k >= 3) {
                        print("continue inner");
                        continue inner;
                    }
                }
            }//inner
        }//outer
    }

    static void continueFor () {

        int i = 0;
        outer:
        for (; true; i++) {
            System.out.println("Enter outer as i = " + i);
            inner:
            for (; i < 10; i++) {
                System.out.println("Enter inner as i = " + i);
                if (i == 2) {
                    System.out.println("continue");
                    continue;
                }

                if (i == 5) {
                    System.out.println("continue outer");
                    continue outer;
                }

                if (i == 7) {
                    System.out.println("break");
                    break;
                }

                if (i == 9) {
                    System.out.println("break outer");
                    break outer;
                }

                System.out.println("End of inner as i = " + i);
            }

            System.out.println("End of outer as i = " + i);
        }

        System.out.println("Next statement after loop!");
    }

    public static void main(String[] args) {

        for (int i = 1; i <= 100; i++) {
            if (i == 99)
                break;
            System.out.print(i + " ");
        }
        System.out.println();
        printInt();
        System.out.println();

        labelFor();
        System.out.println();

        continueFor();
    }
}
