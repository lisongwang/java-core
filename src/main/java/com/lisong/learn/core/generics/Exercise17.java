package com.lisong.learn.core.generics;

import com.lisong.learn.core.util.Sets;

import java.util.EnumSet;
import java.util.Set;

import static com.lisong.learn.core.util.Print.print;

public class Exercise17 {

    public enum Watercolors {
        ZINC, LEMON_YELLOW, MEDIUM_YELLOW, DEEP_YELLOW,
        ORANGE, BRILLIANT_RED, CRIMSON, MAGENTA, ROSE_MADDER,
        VIOLET, CERULEAN_BLUE_HUE, PHTHALO_BLUE, ULTRAMARINE,
        COBALT_BLUE_HUE, PERMANENT_GREEN, VIRIDIAN_HUE,
        SAP_GREEN, YELLOW_OCHRE, BURNT_SIENNA, RAW_UMBER,
        BURNT_UMBER, PAYNES_GRAY, IVORY_BLACK
    }

    public static void main(String[] args) {

        Set<Watercolors> set1 = EnumSet.range(Watercolors.BRILLIANT_RED, Watercolors.VIRIDIAN_HUE);
        Set<Watercolors> set2 = EnumSet.range(Watercolors.CERULEAN_BLUE_HUE, Watercolors.BURNT_UMBER);
        print("set1: " + set1);
        print("set2: " + set2);
        print("union(set1, set2): " + Sets.union(set1, set2));
        Set<Watercolors> subset = Sets.intersection(set1, set2);
        print("intersection(set1, set2): " + subset);
        print("difference(set1, subset): " + Sets.difference(set1, subset));
        print("difference(set2, subset): " + Sets.difference(set2, subset));
        print("complement(set1, set2): " + Sets.complement(set1, set2));
    }
}
