package com.lisong.learn.core.enumerated;

import com.lisong.learn.core.enumerated.enums.Input;
import com.lisong.learn.core.enumerated.enums.InputEnum;
import com.lisong.learn.core.enumerated.util.VendingMachine;
import com.lisong.learn.core.util.Generator;

import java.io.IOException;
import java.util.Random;

public class Exercise11 {
    private static final Random rand = new Random(988);
    private static Generator<Input> gen = ()->{
        if(rand.nextInt(2) == 0) {
            return InputEnum.randomSelect();
        }else {
            return VendingMachine.items.get(rand.nextInt(VendingMachine.items.size()));
        }
    };
    public static void main(String[] args) {
        String fileName = "JavaCore/src/main/java/com/lisong/learn/core/enumerated/file/vendingItemList.txt";
        if(args.length != 0)
            fileName = args[0];
        try {
            VendingMachine.init(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        VendingMachine.run(gen);
    }
}