package com.lisong.learn.core.enumerated.enums;

import java.util.EnumMap;

import static com.lisong.learn.core.enumerated.enums.InputEnum.*;

public enum Category {

    MONEY(NICKEL,DIME,QUARTER,DOLLAR),
    //ITEM_SELECTION(TOOTHPASTE,CHIPS,SODA,SOAP),
    ITEM_SELECTION,
    QUIT_TRANSACTION(ABORT_TRANSACTION),
    SHUT_DOWN(STOP);

    private InputEnum[] inputs;
    Category(InputEnum... inputs) {
        this.inputs = inputs;
    }

    private static EnumMap<InputEnum,Category> categories = new EnumMap<>(InputEnum.class);
    static {
        for(Category c : values()) {
            for(InputEnum i : c.inputs)
                categories.put(i,c);
        }
    }

    public static Category categorize(Input input) {
        return categories.get(input);
    }
}