package com.lisong.learn.core.access;

import com.lisong.learn.core.access.defaultpkg.AccessDemo;

/**
 * Display the access control on each level.
 */
public class Exercise5 {

    public static void main(String[] args) {

        AccessDemo ac = AccessDemo.createInstance("Mary", "18", "140102456", "780000");
        System.out.println(ac.name);
        //System.out.println(ac.age);
        //System.out.println(ac.phone);
        //System.out.println(ac.salary);
        ac.sayName();
        //ac.sayAge();
        //ac.sayPhone();
        //ac.saySalary();
    }
}
