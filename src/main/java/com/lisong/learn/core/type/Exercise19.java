package com.lisong.learn.core.type;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static com.lisong.learn.core.util.Print.print;

public class Exercise19 {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        try {
            Class<Toy> clazz = (Class<Toy>)Class.forName("com.lisong.learn.core.type.Toy");
            Constructor<Toy> c = clazz.getDeclaredConstructor(int.class);
            Toy toy = c.newInstance(10);
            print("Create object of class " + toy.getClass().getName());
        }catch(ClassNotFoundException e) {
            print("Class not found");
        }catch(NoSuchMethodException e) {
            print("Constructor can't be found");
        }catch(InstantiationException e) {
            print("Can't instantiate");
        }catch(IllegalAccessException e) {
            print("Cant access");
        }catch(InvocationTargetException e) {
            print("Exception thrown from Constructor");
        }
    }
}
