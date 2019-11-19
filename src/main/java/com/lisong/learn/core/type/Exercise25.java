package com.lisong.learn.core.type;

import com.lisong.learn.core.type.hide.HiddenClass;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static com.lisong.learn.core.util.Print.print;

public class Exercise25 {

    public static void main(String[] args) {

        HiddenClass hc = new HiddenClass();
        try {
            Method f = hc.getClass().getDeclaredMethod("f");
            f.setAccessible(true);
            f.invoke(hc);
            Method g = hc.getClass().getDeclaredMethod("g", int.class);
            g.setAccessible(true);
            g.invoke(hc, 2);
            Method t = hc.getClass().getDeclaredMethod("t", String.class, boolean.class);
            t.setAccessible(true);
            t.invoke(hc, "ok", true);
        } catch (NoSuchMethodException e) {
            print("Method not found");
        } catch (IllegalAccessException e) {
            print("Can't access");
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
