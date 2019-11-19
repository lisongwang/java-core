package com.lisong.learn.core.generics.tuple;

import java.util.Objects;

public class SixTuple<A, B, C, D, E, F> extends FiveTuple<A, B, C, D, E> {

    public final F f;

    public SixTuple(A a, B b, C c, D d, E e, F f) {
        super(a, b, c, d, e);
        this.f = f;
    }

    @Override
    public int compareTo(Object o) {
        if(this.getClass() == o.getClass()) {
            SixTuple tt = (SixTuple)o;
            int result = super.compareTo(o);
            if(result != 0)
                return result;
            return ((Comparable)f).compareTo(tt.f);
        }
        throw new ClassCastException();
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 37*result + (f == null ? 0 : f.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(hashCode() != obj.hashCode())
            return false;
        if(this.getClass() == obj.getClass()) {
            SixTuple tt = (SixTuple)obj;
            return super.equals(obj) && Objects.equals(f,tt.f);
        }
        return false;
    }

    @Override
    public String toString() {
        return a + " " + b + " " + c + " " + d + " " + e + " " + f + " " + hashCode();
    }
}