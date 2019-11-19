package com.lisong.learn.core.reusing;

import com.lisong.learn.core.reusing.pretected.ProtectedDemo;

/**
 * Show how protected field works.
 */
public class Exercise15 extends ProtectedDemo {

    void changeName(String name) { this.setName(name); }

    public static void main(String[] args) {

        // can't access protected method outside the package
        ProtectedDemo pd = new Exercise15();
        //pd.setName("James");

        Exercise15 exe15 = new Exercise15();
        exe15.changeName("James"); // private method wouldn't be override
        System.out.println(exe15.getName());
    }
}

