package com.lisong.learn.core.reusing.pretected;

public class ProtectedDemo {

    private String name;

    protected String getName() { return this.name; }
    protected void setName(String name) { this.name = name; }

    void changeName(String name) { this.name = name; }
}
