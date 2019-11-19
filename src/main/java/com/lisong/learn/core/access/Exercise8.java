package com.lisong.learn.core.access;

import com.lisong.learn.core.access.defaultpkg.Connection;
import com.lisong.learn.core.access.defaultpkg.ConnectionManager;
import com.lisong.learn.core.access.defaultpkg.Widget;

/**
 * Combine exercise 7, exercise 8.
 */
public class Exercise8 {

    public static void main(String[] args) {

        //Widget wg = new Widget();
        Widget.fly();

        for (int i = 0; i < 10; i++) {
            Connection conn = ConnectionManager.getConnectionManager().getConnection();
            conn.connect();
            //conn.release();
        }

        ConnectionManager.getConnectionManager().getConnection().connect(); //wouldn't work as out of resource.
    }
}
