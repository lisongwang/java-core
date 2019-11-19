package com.lisong.learn.core.initialization;

/**
 * Combine exercise 21, exercise 22.
 */
public class Exercise22 {

    public static void main(String[] args) {

        for (Currency cur : Currency.values()) {
            System.out.println(cur + " ordinal " + cur.ordinal());

            switch (cur) {
                case CENT: System.out.println("美国");break;
                case FENG: System.out.println("中国");break;
                case PENNY: System.out.println("英国");break;
                case GABLE: System.out.println("俄罗斯");break;
                case NEWBORN: System.out.println("法国");break;
                case SATANG: System.out.println("泰国");break;
                default: System.out.println("非法货币类型");
            }
        }
    }
}

enum Currency {
    CENT, PENNY, GABLE, NEWBORN, SATANG, FENG
}