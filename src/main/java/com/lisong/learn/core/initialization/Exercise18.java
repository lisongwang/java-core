package com.lisong.learn.core.initialization;

/**
 * Combine exercise 16, exercise 17, exercise 18.
 */
public class Exercise18 {

    Exercise18 (String s) {

        System.out.print(s + " ");
    }
    public static void main(String[] args) {

        String[] sa = new String[26];
        for (int i = 0; i < sa.length; i++) {
            sa[i] = new Character((char)('A' + i)).toString();
        }

        for (String s : sa) {
            System.out.print(s + " ");
        }
        System.out.println();

        Exercise18[] exeArray = new Exercise18[26];
        for (int j = 0; j < exeArray.length; j++) {
            exeArray[j] = new Exercise18(new Character((char)('A' + j)).toString());
        }
    }
}
