package com.lisong.learn.core.generics;

import com.lisong.learn.core.type.pet.Cat;
import com.lisong.learn.core.type.pet.Pets;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static com.lisong.learn.core.util.Print.print;

public class Exercise40 {

    public static void main(String[] args) {
        Apply.apply(new Pets().listPets(10), "speak");
        List<Cat> catList = new ArrayList<>();
    }
}

class Apply {

    static <T> void apply(Iterable<? extends T> seq, String methodName, Object... args) {
        for(T t : seq) {
            try {
                Method m = t.getClass().getMethod(methodName);
                m.invoke(t, args);
            } catch (NoSuchMethodException e) {
                print(t + " doesn't speak!");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}