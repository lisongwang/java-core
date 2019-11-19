package com.lisong.learn.core.polymorphism.animal;

import static com.lisong.learn.core.util.Print.print;

public class SharedMember {

    private long refcount;
    private String name;

    public void addRef() { refcount++; }
    public void dispose() {
        if(--refcount == 0)
            print("Disposing " + this);
    }

    public SharedMember(String name) {
        this.name = name;
        print("Creating " + this);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    protected void finalize() throws Throwable {
        if(refcount == 0)
            print("SharedMember " + name + " is not in use");
        else
            print("SharedMember " + name + " still in use");
    }
}
