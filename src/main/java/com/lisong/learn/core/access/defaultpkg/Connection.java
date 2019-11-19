package com.lisong.learn.core.access.defaultpkg;

public class Connection {

    public void connect() { System.out.println("Connected!");}

    Connection() {}

    private ConnectionStatus status = ConnectionStatus.IDLE;

    boolean isAlive() {return status == ConnectionStatus.ACTIVE;}
    boolean isIdle() { return status == ConnectionStatus.IDLE;}
    void active() { status = ConnectionStatus.ACTIVE;}
    public void sleep() {
        status = ConnectionStatus.SLEEPED;
    }
    public void release() {
        status = ConnectionStatus.IDLE;
    }
}
