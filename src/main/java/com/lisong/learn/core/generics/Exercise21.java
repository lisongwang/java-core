package com.lisong.learn.core.generics;

import com.lisong.learn.core.generics.coffee.Coffee;
import com.lisong.learn.core.generics.coffee.Mocha;

import java.util.HashMap;
import java.util.Map;

import static com.lisong.learn.core.util.Print.print;

public class Exercise21 {

    public static void main(String[] args) {

        ClassTypeCapture<String> stringType = new ClassTypeCapture<>(String.class);
        print(stringType.isType("abc"));
        print(stringType.isType(20));

        ClassTypeCapture<Coffee> coffeeType = new ClassTypeCapture<>(Coffee.class);
        coffeeType.addType("Coffee", Mocha.class);
        Object coffee = coffeeType.createNew("Coffee");
        print(coffeeType.isType(coffee));
    }
}

class ClassTypeCapture<T> {

    Class<T> type;
    ClassTypeCapture(Class<T> clazz) { type = clazz; }

    boolean isType(Object obj) { return type.isInstance(obj); }

    Map<String, Class<?>> typeMap = new HashMap<>();

    void addType(String typeName, Class<?> clazz) {
        typeMap.put(typeName, clazz);
    }

    Object createNew(String typeName) {
        try {
            return typeMap.get(typeName).newInstance();
        } catch (NullPointerException e) {
            print("type name: " + typeName + " does not exists.");
        } catch (Exception e) {
            print("Initiate instance failed");
        }
        return null;
    }
}

