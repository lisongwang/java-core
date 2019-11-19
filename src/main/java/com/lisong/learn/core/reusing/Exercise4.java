package com.lisong.learn.core.reusing;

import static com.lisong.learn.core.util.Print.print;

/**
 * Combine exercise 3, exercise 4.
 */
public class Exercise4 {

    public static void main(String[] args) {

        Carton x = new Carton();
    }
}

class Art {

    Art() { print("Art constructor"); }
    {
        print("Art instance");
    }
    static {
        print("Art static");
    }
}

class Drawing extends Art {

    Drawing() { print("Drawing constructor"); }
    {
        print("Drawing instance");
    }
    static {
        print("Drawing static");
    }
}

class Carton extends Drawing {

    Carton() { print("Carton constructor"); }
    {
        print("Carton instance");
    }
    static {
        print("Carton static");
    }
}