package com.lisong.learn.core.operators;

import static com.lisong.learn.core.util.Print.print;

/**
 * Compare Strings.
 */
public class Exercise14 {

    /**
     * Compare two strings.
     * @param s1 src string
     * @param s2 des string
     * @return 0, 1, 2, 3
     */
    static int compare(String s1, String s2) {

        if ((s1 == null) || (s2 == null)) {
            return 0;
        }

        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();

        if (c1.length > c2.length) {
            return 1;
        } else if (c1.length < c2.length) {
            return 2;
        } else {
            for(int i = 0; i < c1.length; i++) {
                if (c1[i] > c2[i]) {
                    return 1;
                } else if (c1[i] < c2[i]) {
                    return 2;
                } else {
                    continue;
                }
            }

           return 3;
        }
    }

    static void f(boolean b) {
        print(b? true : false);
    }

    public static void main(String[] args) {

        String s1 = "ABCD";
        String s2 = "ABCD";
        String s3 = new StringBuffer("ABCD").toString();

        int result = compare(s1, s2);
        if (result == 0)
            print("Can't compare null string!");
        else if (result == 1)
            print(s1 + " > " + s2);
        else if (result == 2)
            print(s1 + " < " + s2);
        else if (result == 3)
            print(s1 + " = " + s2);
        else
            print("Undefined result!");

        f(s1 == s2);
        f(s1 == s3);
        f(s1.equals(s2));
        f(s1.equals(s3));
    }
}
