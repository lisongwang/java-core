package com.lisong.learn.core.generics.tuple;

import java.util.Objects;

public class FourTuple<A,B,C,D> extends ThreeTuple<A, B, C> {

    public final D d;

    FourTuple(A a, B b, C c, D d) {
        super(a, b, c);
        this.d = d;
    }

    @Override
    public int compareTo(Object o) {
        if(this.getClass() == o.getClass()) {
            FourTuple tt = (FourTuple)o;
            int result = super.compareTo(o);
            if(result != 0)
                return result;
            return ((Comparable)d).compareTo(tt.d);
        }
        throw new ClassCastException();
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 37*result + (d == null ? 0 : d.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(hashCode() != obj.hashCode())
            return false;
        if(this.getClass() == obj.getClass()) {
            FourTuple tt = (FourTuple)obj;
            return super.equals(obj) && Objects.equals(d,tt.d);
        }
        return false;
    }

    @Override
    public String toString() {
        return a + " " + b + " " + c + " " + d + " " + hashCode();
    }
}