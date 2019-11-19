package com.lisong.learn.core.generics;

import static com.lisong.learn.core.util.Print.print;

/**
 * Show the classic decoration design pattern
 */
public class Exercise38 {

    public static void main(String[] args) {

        print("I want a steam milked coffee.");
        SteamedMilk sc = new SteamedMilk(new BasicCoffee()).make();
        print("The price is: " + sc.price());

        print("I want a Foam coffee with Chocolate.");
        Foam fo = new Foam(new Chocolate(new BasicCoffee())).make();
        print("The price is: " + fo.price());

        print("I want the Queen coffee.");
        WhippedCream wc = new WhippedCream(new Caramel(new Chocolate(new Foam(new SteamedMilk(new BasicCoffee()))))).make();
        print("The price is: " + wc.price());
    }
}

class BasicCoffee {
    private static final double PRICE = 20.0;
    BasicCoffee make() {
        print("Make a simple Coffee");
        return new BasicCoffee();
    }

    protected double price() {
        return PRICE;
    }
}

abstract class CoffeeDecorator extends BasicCoffee {
    BasicCoffee cc;
    CoffeeDecorator(BasicCoffee cc) {
        this.cc = cc;
    }
}

class SteamedMilk extends CoffeeDecorator {
    private static final double FEE1 = 10;
    SteamedMilk(BasicCoffee cc) {
        super(cc);
    }

    @Override
    SteamedMilk make() {
        cc.make();
        print("Add some steamed milk to the Coffee");
        return this;
    }

    @Override
    public double price() {
        return cc.price() + FEE1;
    }
}

class Foam extends CoffeeDecorator {
    private static final double FEE2 = 15;
    Foam(BasicCoffee cc) {
        super(cc);
    }

    @Override
    Foam make() {
        cc.make();
        print("Add some Foam to the Coffee");
        return this;
    }

    @Override
    public double price() {
        return cc.price() + FEE2;
    }
}

class Chocolate extends CoffeeDecorator {
    private static final double FEE3 = 18;
    Chocolate(BasicCoffee cc) {
        super(cc);
    }

    @Override
    Chocolate make() {
        cc.make();
        print("Add some Chocolate to the Coffee");
        return this;
    }

    @Override
    public double price() {
        return cc.price() + FEE3;
    }
}

class Caramel extends CoffeeDecorator {
    private static final double FEE4 = 25;
    Caramel(BasicCoffee cc) {
        super(cc);
    }

    @Override
    Caramel make() {
        cc.make();
        print("Add some Caramel to the Coffee");
        return this;
    }

    @Override
    public double price() {
        return cc.price() + FEE4;
    }
}

class WhippedCream extends CoffeeDecorator {
    private static final double FEE5 = 30;
    WhippedCream(BasicCoffee cc) {
        super(cc);
    }

    @Override
    WhippedCream make() {
        cc.make();
        print("Add some WhippedCream to the Coffee");
        return this;
    }

    @Override
    public double price() {
        return cc.price() + FEE5;
    }
}