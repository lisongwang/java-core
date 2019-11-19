package com.lisong.learn.core.control;

public class Exercise10 {

    static boolean findDigital (char[] src, char[] tar) {

        if (src.length == 0)
            return false;

        outer:
        for (int i = 0; i < src.length; i++) {

            for (int j = 0; j < tar.length; j++) {
                if (src[i] == tar[j]) {
                    tar[j] = 'a'; //占位符
                    continue outer;
                }
            }
            return false;
        }
        return true;
    }

    static boolean correctDivisor (int di1, int di2, int num) {

        char[] numArray = new Integer(num).toString().toCharArray();
        char[] cArray1 = new Integer(di1).toString().toCharArray();
        char[] cArray2 = new Integer(di2).toString().toCharArray();

        //位数不对，直接返回
        if ((cArray1.length != cArray2.length) || (cArray1.length != numArray.length/2))
            return false;

        //第一个除数数字是否都在
        if(!findDigital(cArray1, numArray))
            return false;
        //第二个除数数字是否都在
        if(!findDigital(cArray2, numArray))
            return false;

        return true;
    }

    static boolean divided (int num) {

        String sn = new Integer(num).toString();
        if (sn.endsWith("00"))
            return false;

        int cnt = sn.length();
        if (cnt%2 != 0)
            return false;

        int top = new Double(Math.pow(10, cnt/2)).intValue();
        int low = new Double(Math.pow(10, cnt/2 - 1)).intValue();

        while (low < top) {
            if ((num%low == 0) && (correctDivisor (low, num/low, num)))
                return true;
            low++;
        }
        return false;
    }

    static void findVampireNumber (int digitalNum) {

        if (digitalNum%2 != 0)
            System.out.println("位数不能为奇数！");

        if (digitalNum == 0)
            System.out.println("位数不能为0！");

        int top = new Double(Math.pow(10, digitalNum)).intValue();
        int low = new Double(Math.pow(10, digitalNum - 1)).intValue();

        while (low < top) {

            if (divided(low))
                System.out.println(low);
            low++;
        }
    }

    public static void main(String[] args) {

        int digNum = 4;
        System.out.println("寻找" + digNum + "位数字的vampire numbers!");
        findVampireNumber(digNum);
    }
}
