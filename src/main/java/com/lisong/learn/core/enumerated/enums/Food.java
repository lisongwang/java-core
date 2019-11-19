package com.lisong.learn.core.enumerated.enums;

public interface Food {
    enum Appetizer implements Food {
        SALAD, SOUP, SPRING_ROLLS
    }
    enum MainCourse implements Food {
        BURRITO, PAD_THAI, LENTILS
    }
    enum Dessert implements Food {
        BLACK_FOREST_CAKE, FRUIT, CREME_CARAMEL
    }
    enum Coffee implements Food {
        BLACK_COFFEE, LATTE, CAPPUCCINO, TEA, ESPRESSO
    }
    enum Wine implements Food {
        RED, WHITE
    }
}