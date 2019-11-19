package com.lisong.learn.core.generics.tuple;

import java.util.Objects;

public class ThreeTuple<A,B,C> extends TwoTuple<A, B> {

    public final C c;

    ThreeTuple(A a, B b, C c) {
        super(a, b);
        this.c = c;
    }

    @Override
    public int compareTo(Object o) {
        if(this.getClass() == o.getClass()) {
            ThreeTuple tt = (ThreeTuple)o;
            int result = super.compareTo(o);
            if(result != 0)
                return result;
            return ((Comparable)c).compareTo(tt.c);
        }
        throw new ClassCastException();
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 37*result + (c == null ? 0 : c.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(hashCode() != obj.hashCode())
            return false;
        if(this.getClass() == obj.getClass()) {
            ThreeTuple tt = (ThreeTuple)obj;
            return super.equals(obj) && Objects.equals(c,tt.c);
        }
        return false;
    }

    @Override
    public String toString() {
        return a + " " + b + " " + c + " " +  hashCode();
    }
}