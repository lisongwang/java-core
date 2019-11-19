package com.lisong.learn.core.generics;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static com.lisong.learn.core.util.Print.print;

public class Exercise22 {

    public static void main(String[] args) {

        GenerateCreator<Item> itemGen = new GenerateCreator<>(Item.class);
        Item item = itemGen.createWithArgs(new Class[]{long.class, String.class, double.class}, new Object[]{100, "test", 250.66});
        print(item);
    }
}

class GenerateCreator<T> {

    private final Class<T> type;
    GenerateCreator(Class<T> clazz) { type = clazz; }

    T createWithArgs(Class<?>[] argsTypes, Object[] args) {

        try {
            Constructor<T> con =  type.getDeclaredConstructor(argsTypes);
            con.setAccessible(true);
            return con.newInstance(args);
        } catch (NoSuchMethodException e) {
            print("Can not find such constructor");
        } catch (IllegalAccessException e) {
            print("Do not have access to this constructor");
        } catch (InstantiationException e) {
            print("Can not instantiate the instance");
        } catch (InvocationTargetException e) {
            print("Get exception from the constructor");
        }
        return null;
    }
}
