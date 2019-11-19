package com.lisong.learn.core.strings;

import static com.lisong.learn.core.util.Print.print;

public class Exercise1 {

    public static void main(String[] args) {

        SprinkleSystem ss = new SprinkleSystem();
        print(ss);
    }
}

class WaterSource {

    private String s;
    WaterSource() {
        print("WaterSource()");
        s = "Constructed";
    }

    @Override
    public String toString() {
        return s;
    }
}

class SprinkleSystem {

    private String valve1, valve2, valve3, valve4;
    private WaterSource w = new WaterSource();
    private int i;
    private float f;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("valve1 = ");
        sb.append(valve1);
        sb.append(" valve2 = ");
        sb.append(valve2);
        sb.append(" valve3 = ");
        sb.append(valve3);
        sb.append(" valve4 = ");
        sb.append(valve4);
        sb.append("\n");
        sb.append("i = ");
        sb.append(i);
        sb.append(" f = " + f);
        //sb.append(f);
        sb.append(" source = ");
        sb.append(w.toString());
        return sb.toString();
    }
}
