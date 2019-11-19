package com.lisong.learn.core.generics;

import com.lisong.learn.core.generics.tuple.TwoTuple;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

import static com.lisong.learn.core.generics.tuple.Tuple.tuple;
import static com.lisong.learn.core.util.Print.print;

public class Exercise39 {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        TwoTuple<Object, Class<?>>[] pairs = new TwoTuple[] {
                tuple(new BasicImpl(), Basic.class),
                tuple((TimeStamp)(new Date()::getTime), TimeStamp.class),
                tuple(new SerialNumberedImpl(), SerialNumbered.class),
                tuple(new Colored() {
                    private String color;
                    @Override
                    public void setColor(String c) { color = c; }
                    @Override
                    public String getColor() { return color; }
                }, Colored.class),
        };

        Object proxyMixin = MixinProxyInvoker.newInstance(pairs);
        ((Basic)proxyMixin).set("test for proxy Minx");
        ((Colored)proxyMixin).setColor("Yellow");
        print(((Basic)proxyMixin).get() + " " + ((TimeStamp)proxyMixin).getStamp() + " " +
                ((SerialNumbered)proxyMixin).getSerialNumber() + " " + ((Colored)proxyMixin).getColor());
    }
}

class MixinProxyInvoker implements InvocationHandler {

    private Map<String, Object> methodMap;
    MixinProxyInvoker(TwoTuple<Object, Class<?>>... pairs) {
        methodMap = new HashMap<>();
        for(TwoTuple<Object, Class<?>> pair : pairs) {
            for(Method m : pair.b.getMethods()) {
                String name = m.getName();
                if(!methodMap.containsKey(name))
                    methodMap.put(name, pair.a);
            }
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(methodMap.get(method.getName()), args);
    }

    public static Object newInstance(TwoTuple<Object, Class<?>>... pairs) {

        Class<?>[] interfaces = new Class<?>[pairs.length];
        for(int i = 0; i < interfaces.length; i++) {
            interfaces[i] = pairs[i].b;
        }
        return Proxy.newProxyInstance(pairs[0].a.getClass().getClassLoader(),
                interfaces, new MixinProxyInvoker(pairs));
    }
}