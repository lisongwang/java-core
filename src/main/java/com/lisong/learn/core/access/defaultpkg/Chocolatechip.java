package com.lisong.learn.core.access.defaultpkg;

/**
 * access the protected method within the same package
 */
public class Chocolatechip {

    public void chomp () {
        Cookie co = new Cookie();
        co.bite();
    }
}
