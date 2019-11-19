package com.lisong.learn.core.enumerated;

import com.lisong.learn.core.enumerated.enums.InputEnum;
import com.lisong.learn.core.enumerated.util.VendingMachine2;

public class Exercise10 {

    public static void main(String[] args) {
        new VendingMachine2().run(InputEnum::randomSelect);
    }
}