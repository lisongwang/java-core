package com.lisong.learn.core.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.lisong.learn.core.util.Print.print;

public class Exercise30 {

    private static void saveShapes(String fileName) throws IOException {
        /*List<Class<? extends Shape>> shapeTypes = new ArrayList<>();
        shapeTypes.add(Circle.class);
        shapeTypes.add(Square.class);
        shapeTypes.add(Line.class);*/
        List<Shape> shapes = new ArrayList<>();
        for(int i = 0; i < 10; i++)
            shapes.add(Shape.randomFactory());
        for(int i = 0; i < 10; i++)
            shapes.get(i).setColor(Shape.BLUE);
        print(shapes);
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
        //out.writeObject(shapeTypes);
        Circle.serializeStaticState(out);
        Square.serializeStaticState(out);
        Line.serializeStaticState(out);
        out.writeObject(shapes);
        out.close();
    }

    @SuppressWarnings("unchecked")
    private static void recoverShapes(String fileName) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
        //List<Class<? extends Shape>> shapeTypes = (List<Class<? extends Shape>>)in.readObject();
        Circle.deserializeStaticState(in);
        Square.deserializeStaticState(in);
        Line.deserializeStaticState(in);
        List<Shape> shapes = (List<Shape>)in.readObject();
        print(shapes);
        in.close();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        if(args.length < 1) {
            System.err.println("Please input the store file!");
            System.exit(0);
        }
        //saveShapes(args[0]);
        recoverShapes(args[0]);
    }
}

abstract class Shape implements Serializable {
    static final int RED = 1, BLUE = 2, GREEN = 3;
    abstract void setColor(int color);
    abstract int getColor();
    private int xPos, yPos, dimension;
    private static Random rand = new Random(77);
    private static int count = 0;
    Shape(int xPos, int yPos, int dimension) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.dimension = dimension;
    }

    @Override
    public String toString() {
        return getClass() + " color[" + getColor() + "] xPos[" + xPos +
                "] yPos[" + yPos + "] dim[" + dimension + "]\n";
    }

    static Shape randomFactory() {
        int xPos = rand.nextInt(100);
        int yPos = rand.nextInt(100);
        int dimension = rand.nextInt(100);
        switch(count++ % 3) {
            default:
            case 0: return new Circle(xPos, yPos, dimension);
            case 1: return new Square(xPos, yPos, dimension);
            case 2: return new Line(xPos, yPos, dimension);
        }
    }
}

class Circle extends Shape {
    private static int color = RED;

    Circle(int xPos, int yPos, int dimension) {
        super(xPos, yPos, dimension);
    }

    @Override
    void setColor(int color) {
        Circle.color = color;
    }

    @Override
    int getColor() {
        return color;
    }

    static void serializeStaticState(ObjectOutputStream out) throws IOException {
        out.writeInt(color);
    }

    static void deserializeStaticState(ObjectInputStream in) throws IOException {
        color = in.readInt();
    }
}

class Square extends Shape {
    private static int color;

    Square(int xPos, int yPos, int dimension) {
        super(xPos, yPos, dimension);
        color = RED;
    }

    @Override
    void setColor(int color) {
        Square.color = color;
    }

    @Override
    int getColor() {
        return color;
    }

    static void serializeStaticState(ObjectOutputStream out) throws IOException {
        out.writeInt(color);
    }

    static void deserializeStaticState(ObjectInputStream in) throws IOException {
        color = in.readInt();
    }
}

class Line extends Shape {
    private static int color = RED;

    Line(int xPos, int yPos, int dimension) {
        super(xPos, yPos, dimension);
    }

    @Override
    void setColor(int color) {
        Line.color = color;
    }

    @Override
    int getColor() {
        return color;
    }

    static void serializeStaticState(ObjectOutputStream out) throws IOException {
        out.writeInt(color);
    }

    static void deserializeStaticState(ObjectInputStream in) throws IOException {
        color = in.readInt();
    }
}