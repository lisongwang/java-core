package com.lisong.learn.core.io;

import java.io.*;

import static com.lisong.learn.core.util.Print.print;

public class Exercise28 implements Externalizable {

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        print("Blip2.writeExternal");
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        print("Blip2.readExternal");
    }
}

class Blip1 implements Externalizable {

    public Blip1() {
        print("Blip1 Constructor");
    }
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        print("Blip1.writeExternal");
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        print("Blip1.readExternal");
    }
}

class Blip2 {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        if(args.length < 1) {
            System.err.println("Please input the store file!");
            System.exit(0);
        }
        print("Constructing objects: ");
        Blip1 b1 = new Blip1();
        Exercise28 b2 = new Exercise28();
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(args[0]));
        print("Saving objects: ");
        out.writeObject(b1);
        out.writeObject(b2);
        out.close();

        ObjectInputStream in = new ObjectInputStream(new FileInputStream(args[0]));
        print("Recovering b1: ");
        b1 = (Blip1)in.readObject();
        print("Recovering b2: ");
        b2 = (Exercise28)in.readObject();
    }
}