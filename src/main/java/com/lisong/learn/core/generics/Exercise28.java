package com.lisong.learn.core.generics;

import com.lisong.learn.core.type.pet.Cat;
import com.lisong.learn.core.type.pet.Pet;

import java.util.ArrayList;
import java.util.List;

import static com.lisong.learn.core.util.Print.print;

public class Exercise28 {

    private static <T> void setupRocket(Rocket<T> g1, Booster<? extends T> g2) { g1.setBooster(g2); }

    private static <T> T generationOfRocket(Rocket<T> g1) {
        T t = g1.getBoosterGeneration();
        print("Rocket boost's generation is: " + t.getClass().getTypeName());
        return t;
    }

    private static <T> void test1(T t1, T t2) {
        System.out.println("Doing well");
    }

    private static <T> void test2(List<T> t1, List<T> t2) {
        System.out.println("Doing well");
    }

    private static <T> void test3(List<T> t1, T t) {
        System.out.println("Doing well");
    }

    private static <T> void test3(T t, List<T> t1) {
        System.out.println("Doing well");
    }

    private static <T> void test4(List<T> t1, List<? extends T> t2) {
        System.out.println("Doing well");
    }

    private static <T> void test5(List<T> t1, List<? super T> t2) {
        System.out.println("Doing well");
    }

    private static <T> void test6(List<? extends T> t1, List<? super T> t2) {
        System.out.println("Doing well");
    }

    private static <T> void test7(List<?> t1, List<? super T> t2) {
        System.out.println("Doing well");
    }

    private static <T> void test8(T[] t1, List<T> t2) {
        System.out.println("Doing well");
    }

    public static void testTypeInfer() {

        List<GenerationCurrent> listC = new ArrayList<>();
        List<GenerationNext> listN = new ArrayList<>();

        // T can be inferred to any Object, Primitives, Array.
        test1(listC, listN);
        test1(new Booster(), listC);
        test1(10, 'A');
        test1(new int[]{}, 12.0);

        // T is specified with the list type
        test2(listN, listN);
        //test2(listC, listN);

        // Infer list type first then try cast the simple type
        //test3(listN, new GenerationCurrent());
        test3(listC, new GenerationNext());
        //test3(new GenerationCurrent(), listN);
        test3(new GenerationNext(), listC);

        // ? can be used to loose the constraint
        test4(listC, listN);
        //test4(listN, listC);
        //test5(listC, listN);
        test5(listN, listC);

        test6(listN, listC);
        //test6(listC, listN);

        test7(listN, listC);
        test7(listC, listN);

        test8(new GenerationNext[]{}, listC);
        //test8(new GenerationCurrent[]{}, listN);
    }

    static <T> void covariantTest1(Generic1<? super T> g1, T t) {
        g1.take(t);
    }

    static <T> T covariantTest2(Generic2<? extends T> g2) {
        return g2.get();
    }

    public static void main(String[] args) {

        Rocket<GenerationCurrent> rocketC = new Rocket<>(new GenerationCurrent());

        Booster<GenerationOlder> boosterO = new Booster<GenerationOlder>(){};
        boosterO.setGeneration(new GenerationOlder());
        print("boosterO " + boosterO.getClass().getGenericSuperclass().getTypeName());

        Booster<GenerationNext> boosterN = new Booster<GenerationNext>(){};
        boosterN.setGeneration(new GenerationNext());
        print("boosterN " + boosterN.getClass().getGenericSuperclass().getTypeName());

        setupRocket(rocketC, boosterN); // ok, Rocket works on next generation booster.
        //covariantTest1(rocketC, boosterO); // forbid, Rocket can't work with old generation booster.
        generationOfRocket(rocketC);

        testTypeInfer();

        covariantTest1(new Generic1<Pet>(), new Cat());
        covariantTest2(new Generic2<Pet>());
    }
}

// Rocket's generation must newer than booster's
class Rocket<T> {

    private T generation;
    private Booster<? extends T> booster;

    Rocket(T t) { this.generation = t; }

    Rocket(T generation, Booster<? extends T> booster) {
        this.generation = generation;
        this.booster = booster;
        print("New Rocket with generation: " + this.generation.getClass().getTypeName());
        print("New Rocket with booster: " + this.booster.getClass().getGenericSuperclass().getTypeName());
    }

    void setBooster(Booster<? extends T> g2) {
        booster = g2;
        print("change booster to " + g2.getClass().getGenericSuperclass().getTypeName());
    }

    T getBoosterGeneration() {
        return booster.get();
    }
}

class Booster<T> {
    private T t;

    Booster() {}

    T get() { return t; }

    void setGeneration(T t) { this.t = t; }
}

class GenerationOlder {}
class GenerationCurrent extends GenerationOlder {}
class GenerationNext extends GenerationCurrent {}

class Generic1<T> {
    private T t;
    void take(T t) { this.t = t; }
}

class Generic2<T> {
    private T t;
    T get() { return t; }
}