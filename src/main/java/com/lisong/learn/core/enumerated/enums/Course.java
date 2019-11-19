package com.lisong.learn.core.enumerated.enums;

import com.lisong.learn.core.enumerated.util.Enums;

public enum Course {
    APPETIZER(Food.Appetizer.class),
    MAINCOURSE(Food.MainCourse.class),
    DESSERT(Food.Dessert.class),
    COFFEE(Food.Coffee.class),
    WINE(Food.Wine.class);

    private Food[] values;
    Course(Class<? extends Food> clazz) {
        values = clazz.getEnumConstants();
    }

    public Food randomSelect() {
        return Enums.random(values);
    }
}