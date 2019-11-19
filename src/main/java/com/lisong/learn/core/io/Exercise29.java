package com.lisong.learn.core.io;

import java.io.*;

import static com.lisong.learn.core.util.Print.print;

public class Exercise29 {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        if(args.length < 1) {
            System.err.println("Please input the store file!");
            System.exit(0);
        }
        print("Constructing objects: ");
        Blip3 b3 = new Blip3(47, "A String");
        print(b3);
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(args[0]));
        print("Saving objects: ");
        out.writeObject(b3);
        out.close();

        ObjectInputStream in = new ObjectInputStream(new FileInputStream(args[0]));
        print("Recovering b3: ");
        b3 = (Blip3)in.readObject();
        print(b3);
    }
}

class Blip3 implements Externalizable {
    private int i;
    private String s;
    public Blip3() {
        print("Blip3 Constructor");
    }

    public Blip3(int i, String s) {
        print("Blip3(int i, String s)");
        this.i = i;
        this.s = s;
    }

    @Override
    public String toString() {
        return s + " " + i;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        print("Blip3.writeExternal");
        //out.writeInt(i);
        //out.writeObject(s);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        print("Blip3.readExternal");
        //i = in.readInt();
        //s = (String)in.readObject();
    }
}