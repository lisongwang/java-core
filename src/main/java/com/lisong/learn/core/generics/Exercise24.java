package com.lisong.learn.core.generics;

import java.util.HashMap;
import java.util.Map;

import static com.lisong.learn.core.util.Print.print;

public class Exercise24 {

    public static void main(String[] args) {

        ClassTypeCapture2<Goods> goodType = new ClassTypeCapture2<>(Goods.class);
        goodType.addType("Good", Goods::new);
        goodType.addType("Unit", Unit::new);
        Object good = goodType.createNew("Good", "Good1");
        Object unit = goodType.createNew("Unit", "Unit1");
        print(goodType.isType(good));
        print(goodType.isType(unit));
        print(good);
        print(unit);

        ClassTypeCapture2<Unit> unitType = new ClassTypeCapture2<>(Unit.class);
        //unitType.addType("Good", Goods::new);
        unitType.addType("Unit", Unit::new);
        print(unitType.isType(good));
    }
}

class ClassTypeCapture2<T> {

    Class<T> type;
    ClassTypeCapture2(Class<T> clazz) { type = clazz; }

    boolean isType(Object obj) { return type.isInstance(obj); }

    private Map<String, IFactory<T>> typeMap = new HashMap<>();

    <F extends IFactory<T>> void addType(String typeName, F factory) {
        typeMap.put(typeName, factory);
    }

    Object createNew(String typeName, String name) {
        try {
            return typeMap.get(typeName).create(name);
        } catch (NullPointerException e) {
            print("type name: " + typeName + " does not exists.");
        } catch (Exception e) {
            print("Initiate instance failed");
        }
        return null;
    }
}

class Goods {
    private String name;

    Goods(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

class Unit extends Goods {
    Unit(String name) {
        super(name);
    }
}