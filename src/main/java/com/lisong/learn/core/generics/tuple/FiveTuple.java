package com.lisong.learn.core.generics.tuple;

import java.util.Objects;

public class FiveTuple<A,B,C,D,E> extends FourTuple<A, B, C, D> {

    public final E e;

    FiveTuple(A a, B b, C c, D d, E e) {
        super(a, b, c, d);
        this.e = e;
    }

    @Override
    public int compareTo(Object o) {
        if(this.getClass() == o.getClass()) {
            FiveTuple tt = (FiveTuple)o;
            int result = super.compareTo(o);
            if(result != 0)
                return result;
            return ((Comparable)e).compareTo(tt.e);
        }
        throw new ClassCastException();
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 37*result + (e == null ? 0 : e.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(hashCode() != obj.hashCode())
            return false;
        if(this.getClass() == obj.getClass()) {
            FiveTuple tt = (FiveTuple)obj;
            return super.equals(obj) && Objects.equals(e,tt.e);
        }
        return false;
    }

    @Override
    public String toString() {
        return a + " " + b + " " + c + " " + d + " " + e + " " + hashCode();
    }
}