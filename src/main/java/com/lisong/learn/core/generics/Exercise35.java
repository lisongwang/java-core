package com.lisong.learn.core.generics;

import com.lisong.learn.core.generics.coffee.Coffee;
import com.lisong.learn.core.generics.coffee.CoffeeGenerator;
import com.lisong.learn.core.util.Generators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.lisong.learn.core.util.Print.print;

public class Exercise35 {

    @SuppressWarnings("unchecked")
    private static void addTeaWithCoffeeList(List coffeeList) {
        coffeeList.add(new Tea());
    }

    public static void main(String[] args) {

        List<Coffee> coffeeList = new ArrayList<>();
        Generators.fill(coffeeList, new CoffeeGenerator(), 10);
        addTeaWithCoffeeList(coffeeList); //add tea to coffee list without exception
        print(coffeeList);
        List<Coffee> checkedCoffeeList = Collections.checkedList(new ArrayList<>(), Coffee.class);
        Generators.fill(checkedCoffeeList, new CoffeeGenerator(), 10);
        addTeaWithCoffeeList(checkedCoffeeList);
    }
}

class Tea {
    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}