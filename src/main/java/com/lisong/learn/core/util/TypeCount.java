package com.lisong.learn.core.util;

import java.util.HashMap;
import java.util.Map;

public class TypeCount extends HashMap<Class<?>, Integer> {

    private Class<?> basetype;
    public TypeCount(Class<?> baseType) { this.basetype = baseType; }

    public void count(Object obj) {

        Class<?> c = obj.getClass();
        if(!basetype.isAssignableFrom(c)) {
            throw new RuntimeException(obj.getClass().getName() + " is not extends from " + basetype.getName());
        }
        countClass(c);
    }

    private void countClass(Class<?> c) {
        put(c, get(c) == null ? 1 : get(c) + 1);
        Class<?> superClass = c.getSuperclass();
        if(basetype.isAssignableFrom(superClass))
            countClass(superClass);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(Map.Entry<Class<?>, Integer> entry : entrySet()) {
            sb.append(entry.getKey().getSimpleName() + "=" + entry.getValue() + ", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append("]");
        return sb.toString();
    }
}
