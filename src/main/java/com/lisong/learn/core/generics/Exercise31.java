package com.lisong.learn.core.generics;

public class Exercise31 {

    public static void main(String[] args) {
        Hourly h = new Hourly();
    }
}

interface Payable<T> {}

class Employee implements Payable {}
class Hourly extends Employee implements Payable {}

//class Employee implements Payable<Employee> {}
//class Hourly extends Employee implements Payable<Hourly> {}