package com.lisong.learn.core.generics;

import com.lisong.learn.core.util.Generator;
import com.lisong.learn.core.util.Generators;

import java.util.ArrayList;
import java.util.Random;

import static com.lisong.learn.core.util.Print.print;

public class Exercise19 {

    public static void main(String[] args) {

        print(new CargoShip(2, 3, 4, 5));
    }
}

class Item {

    private final long id;
    private String name;
    private double price;

    Item(long id, String name, double price) {
        this.name = name;
        this.id = id;
        this.price = price;
    }

    @Override
    public String toString() {
        return id + ": " + name + " price: $" + price;
    }
    private static Random rand = new Random(88);
    static Generator<Item> gen = () -> new Item(rand.nextInt(10000),
            "item", Math.round(rand.nextDouble()*1000) + 0.99);
}

class Shelf extends ArrayList<Item> {

    Shelf(int nItems) {
        Generators.fill(this, Item.gen, nItems);
    }
}

class StorageArea extends ArrayList<Shelf> {

    StorageArea(int nShelfs, int nItems) {
        while(nShelfs-- > 0)
            add(new Shelf(nItems));
    }
}

class Deck extends ArrayList<StorageArea> {

    Deck(int nStorageAreas, int nShelfs, int nItems) {
        while(nStorageAreas-- > 0)
            add(new StorageArea(nShelfs, nItems));
    }
}
class Office {}
class CargoShip extends ArrayList<Deck> {

    private Office office = new Office();

    CargoShip(int nDecks, int nStorageAreas, int nShelfs, int nItems) {
        while(nDecks-- > 0)
            add(new Deck(nStorageAreas, nShelfs, nItems));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Deck deck : this) {
            for(StorageArea storageArea : deck) {
                for(Shelf shelf : storageArea) {
                    for(Item item : shelf)
                        sb.append(item + "\n");
                }
            }
        }
        return sb.toString();
    }
}