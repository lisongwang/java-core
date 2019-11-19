package com.lisong.learn.core.generics;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.lisong.learn.core.util.Print.print;

public class Exercise42 {

    public static void main(String[] args) {

        List<Food> foodList = Arrays.asList(new Food("bread", 5.2),
                new Food("ham", 30.6),
                new Food("cheese", 25.2));

        List<Drink> drinkList = Arrays.asList(new Drink("Milk", 15.2),
                new Drink("water", 30.6),
                new Drink("orange juice", 5.2),
                new Drink("lemon", 25.2));

        double totalPriceForFood = foodList.stream().mapToDouble(Food::getPrice).sum();
        print("Total price for food: " + totalPriceForFood);
        double totalPriceForDrink = drinkList.stream().mapToDouble(Drink::getPrice).sum();
        print("Total price for drink: " + totalPriceForDrink);

        //combine food and drink
        Optional<Food> meal = foodList.stream().reduce(
                (f1, f2)-> new Food(f1.getName() + " " + f2.getName(), f1.getPrice() + f2.getPrice()));
        print(meal.map(Food::getName).orElse("Unknown Food"));

        Optional<Drink> drink = drinkList.stream().reduce(
                (d1, d2)-> new Drink(d1.getName() + " " + d2.getName(), d1.getPrice() + d2.getPrice()));
        print(drink.map(Drink::getName).orElse("Unknown Drink"));

        print(foodList.stream().map(Food::getName).reduce("Cooked:", (n1,n2)->n1 + " " + n2));
    }
}

class Food {

    private String name;
    private double price;

    Food(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Food: " + name;
    }
}

class Drink {

    private String name;
    private double price;

    Drink(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Drink: " + name;
    }
}