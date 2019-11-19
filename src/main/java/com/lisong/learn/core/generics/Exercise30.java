package com.lisong.learn.core.generics;

import static com.lisong.learn.core.util.Print.print;

public class Exercise30 {

    public static void main(String[] args) {

        Holder<Byte> holder_byte = new Holder<>((byte)120);
        byte b = holder_byte.getObj();
        holder_byte.setObj((byte)15);
        print(holder_byte);

        Holder<Short> holder_short = new Holder<>((short)500);
        short s = holder_short.getObj();
        holder_short.setObj((short)1500);
        print(holder_short);

        Holder<Integer> holder = new Holder<>(0);
        int i = holder.getObj();
        holder.setObj(150000);
        print(holder);

        Holder<Character> holder_char = new Holder<>('A');
        char c = holder_char.getObj();
        holder_char.setObj('B');
        print(holder_char);

        Holder<Float> holder_float = new Holder<>(0.6F);
        float f = holder_float.getObj();
        holder_float.setObj(15.6F);
        print(holder_float);

        Holder<Double> holder_double = new Holder<>(0.5);
        double d = holder_double.getObj();
        holder_double.setObj(15.5);
        print(holder_double);

        Holder<Long> holder_long = new Holder<>(10000000L);
        long l = holder_long.getObj();
        holder_long.setObj(1500000L);
        print(holder_long);
    }
}
