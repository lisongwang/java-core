package com.lisong.learn.core.util;

import java.io.PrintStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.regex.Pattern;

public class ClassInfo {

    private static Pattern p1 = Pattern.compile("\\w+\\.");

    public static void extractClass(Class<?> clazz, PrintStream out) {

        out.println("Class name: " + clazz.getName());
        out.println("Modifiers: " + clazz.getModifiers());
        out.println("Classloader: " + clazz.getClassLoader());

        out.println("Supper Class: ");
        out.println("\t" + clazz.getGenericSuperclass());

        out.println("Interfaces: ");
        Type[] types = clazz.getGenericInterfaces();
        if(types.length == 0)
            out.println("\t" + "None");
        for(Type t : types) {
            out.println("\t" + t);
        }

        out.println("Fields: ");
        Field[] fields = clazz.getFields();
        if(fields.length == 0)
            out.println("\t" + "None");
        for(Field f : clazz.getFields()) {
            out.println("\t" + p1.matcher(f.toString()).replaceAll(""));
        }

        out.println("Methods: ");
        Method[] methods = clazz.getMethods();
        if(methods.length == 0)
            out.println("\t" + "None");
        for(Method m : methods) {
            out.println("\t" + p1.matcher(m.toString()).replaceAll(""));
        }

        out.println("Constructors: ");
        Constructor[] cons = clazz.getConstructors();
        if(cons.length == 0)
            out.println("\t" + "None");
        for(Constructor c : cons) {
            out.println("\t" + p1.matcher(c.toString()).replaceAll(""));
        }

        out.println("Annotations: ");
        Annotation[] ans = clazz.getAnnotations();
        if(ans.length == 0)
            out.println("\t" + "None");
        for(Annotation a : ans) {
            out.println("\t" + a);
        }

        out.println("Class: ");
        Class<?>[] classes = clazz.getClasses();
        if(classes.length == 0)
            out.println("\t" + "None");
        for(Class cc : classes) {
            out.println("\t" + cc.getName());
        }

        out.println("DeclaredFields: ");
        Field[] dfs = clazz.getDeclaredFields();
        if(dfs.length == 0)
            out.println("\t" + "None");
        for(Field f : dfs) {
            out.println("\t" + p1.matcher(f.toString()).replaceAll(""));
        }

        out.println("DeclaredMethods: ");
        Method[] dms = clazz.getDeclaredMethods();
        if(dms.length == 0)
            out.println("\t" + "None");
        for(Method m : dms) {
            out.println("\t" + p1.matcher(m.toString()).replaceAll(""));
        }

        out.println("DeclaredConstructors: ");
        Constructor[] dcs = clazz.getDeclaredConstructors();
        if(dcs.length == 0)
            out.println("\t" + "None");
        for(Constructor c : dcs) {
            out.println("\t" + p1.matcher(c.toString()).replaceAll(""));
        }

        out.println("DeclaredAnnotations: ");
        Annotation[] das = clazz.getAnnotations();
        if(das.length == 0)
            out.println("\t" + "None");
        for(Annotation a : das) {
            out.println("\t" + a);
        }

        out.println("DeclaredClass: ");
        Class<?>[] dcss = clazz.getDeclaredClasses();
        if(dcss.length == 0)
            out.println("\t" + "None");
        for(Class cc : dcss) {
            out.println("\t" + cc.getName());
        }

        out.println("TypeParameters: ");
        TypeVariable[] tvs = clazz.getTypeParameters();
        if(tvs.length == 0)
            out.println("\t" + "None");
        for(TypeVariable tv : tvs) {
            out.println("\t" + tv.getName());
        }

        out.println("DeclaringClass: " + clazz.getDeclaringClass());
        out.println("EnclosingConstructor: " + clazz.getEnclosingConstructor());
        out.println("EnclosingClass: " + clazz.getEnclosingClass());
        out.println("EnclosingMethod: " + clazz.getEnclosingMethod());
    }
}