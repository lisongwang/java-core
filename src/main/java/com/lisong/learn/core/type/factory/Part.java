package com.lisong.learn.core.type.factory;

import com.lisong.learn.core.util.Factory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Part {

    private static List<Factory<? extends Part>> factories = new ArrayList<>();
    private static List<Class<? extends Part>> parts = new ArrayList<>();
    Part() {}

    private static void loadFactory() {
        Collections.addAll(factories,
                new FuelFilter.Factory(),
                new CabinAirFilter.Factory(),
                new AirFilter.Factory(),
                new OilFilter.Factory(),
                new FanBelt.Factory(),
                new GeneratorBelt.Factory(),
                new PowerSteeringBelt.Factory(),
                new NullPart.Factory());
    }

    private static void loadParts() {
        Collections.addAll(parts,
                FuelFilter.class,
                CabinAirFilter.class,
                AirFilter.class,
                OilFilter.class,
                FanBelt.class,
                GeneratorBelt.class,
                PowerSteeringBelt.class,
                NullPart.class);
    }

    static {
        loadFactory();
        loadParts();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    private static Random rand = new Random(500);

    public static Part randomPart() {
        return factories.get(rand.nextInt(factories.size())).create();
    }
    public static Part randomPart1() {
        try {
            return parts.get(rand.nextInt(parts.size())).newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}