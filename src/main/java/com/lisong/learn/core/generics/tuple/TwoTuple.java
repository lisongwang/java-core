package com.lisong.learn.core.generics.tuple;

import java.util.Objects;

public class TwoTuple<A, B> implements Comparable{

    public final A a;
    public final B b;

    TwoTuple(A a, B b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public int compareTo(Object o) {
        if(this.getClass() == o.getClass()) {
            TwoTuple tt = (TwoTuple)o;
            int first = ((Comparable)a).compareTo(tt.a);
            if(first != 0)
                return first;
            return ((Comparable)b).compareTo(tt.b);
        }
        throw new ClassCastException();
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 37*result + (a == null ? 0 : a.hashCode());
        result = 37*result + (b == null ? 0 : b.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(hashCode() != obj.hashCode())
            return false;
        if(this.getClass() == obj.getClass()) {
            TwoTuple tt = (TwoTuple)obj;
            return Objects.equals(a,tt.a) && Objects.equals(b,tt.b);
        }
        return false;
    }

    @Override
    public String toString() {
        return a + " " + b + " " + hashCode();
    }
}