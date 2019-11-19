package com.lisong.learn.core.initialization;

/**
 * Combine exercise 10, exercise 11, exercise 12.
 */
public class Exercise12 {

    protected void finalize () {

        System.out.println("Finalize the object!");
    }

    public static void main(String[] args) {

        Exercise12 exe10 = new Exercise12();
        exe10 = null;

        Tank tank = new Tank();
        tank.setFilled(true);
        tank.setEmptied(false);
        tank = null;
        System.gc();
    }
}

class Tank {

    private boolean filled = false;
    private boolean emptied = true;

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public boolean isEmptied() {
        return emptied;
    }

    public void setEmptied(boolean emptied) {
        this.emptied = emptied;
    }

    protected void finalize() {

        if (!this.emptied)
            System.out.println("Tank is not emptied!");
    }
}

