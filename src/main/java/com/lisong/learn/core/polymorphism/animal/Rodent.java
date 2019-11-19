package com.lisong.learn.core.polymorphism.animal;

import static com.lisong.learn.core.util.Print.print;

public abstract class Rodent implements RodentIntf {

    private Characteristic p = new Characteristic(" has tail");
    private Description d = new Description(" small animal");

    protected SharedMember share;

    public Rodent(SharedMember share) {
        this.share = share;
        this.share.addRef();
        print("Creating Rodent");
    }

    public void dispose() {
        print("Disposing Rodent");
        if(share != null)
            share.dispose();
        d.dispose();
        p.dispose();
    }
}
