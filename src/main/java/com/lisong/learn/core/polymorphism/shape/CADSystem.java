package com.lisong.learn.core.polymorphism.shape;

public class CADSystem extends Shape {

    private Circle c;
    private Triangle t;
    private Line[] lines = new Line[3];

    public CADSystem() {
        for(int i = 0; i < lines.length; i++)
            lines[i] = new Line(i, i*i);
        c = new Circle();
        t = new Triangle();
    }

    @Override
    public void dispose() {
        t.dispose();
        c.dispose();
        for(Line line : lines)
            line.dispose();
        super.dispose();
    }
}
