package com.lisong.learn.core.polymorphism;

import static com.lisong.learn.core.util.Print.print;

/**
 * Show the dynamic invoke in constructor is not working as expected.
 */
public class Exercise15 {

    public static void main(String[] args) {

        new RoundGlyph(2);
        new RectangularGlyph(5, 10);
    }
}

class Glyph {

    Glyph() {
        print("Glyph() before draw()");
        draw();
        print("Glyph() after draw()");
        safeDraw();
    }

    protected void draw() {
        print("Glyph.draw");
    }

    private void safeDraw() {
        print("Glyph.safeDraw");
    }
}

class RoundGlyph extends Glyph {

    private int radius = 1;
    RoundGlyph(int r) {
        this.radius = r;
        print("RoundGlyph.RoundGlyph().radius " + radius);
    }

    @Override
    protected void draw() {
        print("RoundGlyph.draw().radius " + radius);
    }
}

class RectangularGlyph extends Glyph {

    private int length = 3;
    private int width = 9;

    RectangularGlyph(int length, int width) {
        this.length = length;
        this.width = width;
        print("RectangularGlyph.RectangularGlyph().length " + this.length + " width " + this.width);
    }
    @Override
    protected void draw() {
        print("RectangularGlyph.draw().length " + this.length + " width " + this.width);
    }
}
