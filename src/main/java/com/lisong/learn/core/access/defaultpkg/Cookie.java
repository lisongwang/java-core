package com.lisong.learn.core.access.defaultpkg;

/**
 * Show the protected method has package access but not public.
 */
public class Cookie {

    public Cookie () { System.out.println("Cookie construcotr");}
    protected void bite() { System.out.println("bite");}
}
