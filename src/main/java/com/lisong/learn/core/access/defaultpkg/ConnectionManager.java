package com.lisong.learn.core.access.defaultpkg;

/**
 * A simple connection pool just working in single thread.
 */
public class ConnectionManager {

    private ConnectionManager() {} //Singleton pattern
    static private ConnectionManager cm = new ConnectionManager();

    private static int size = 10;
    private static Connection[] connectPool;
    static {
        connectPool = new Connection[size];
        for (int i = 0; i < size; i++) {
            connectPool[i] = new Connection();
        }
    }

    public static ConnectionManager getConnectionManager() { return cm;}

    public Connection getConnection() {
        for (Connection cn : connectPool) {
            if (cn.isIdle()) {
                cn.active();
                return cn;
            }
        }
        return null;
    }
}
