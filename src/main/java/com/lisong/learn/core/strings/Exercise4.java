package com.lisong.learn.core.strings;

import java.util.Formatter;

public class Exercise4 {

    public static void main(String[] args) {

        Receipt receipt = new Receipt();
        receipt.printTitle();
        receipt.print("Nike Swing", 10, 2500);
        receipt.print("Nike Flex", 3, 3200.6562);
        receipt.print("Nike Sonic", 1, 800.5);
        receipt.printTotal();
    }
}

class Receipt {

    private Formatter f = new Formatter(System.out);
    private double total = 0;
    private final int item_width = 15;
    private final int qty_width = 5;
    private final int price_width = 10;

    void printTitle() {
        StringBuilder sb = new StringBuilder();
        sb.append("%-").append(item_width).append("s %").append(qty_width).append("s %").append(price_width).append("s\n");
        f.format(sb.toString(), "Item", "Qty", "Price");
        f.format(sb.toString(), "----", "---", "-----");
    }

    void print(String name, int qty, double price) {
        StringBuilder sb = new StringBuilder();
        sb.append("%-").append(item_width).append("s %").append(qty_width).append("d %").append(price_width).append(".2f\n");
        f.format(sb.toString(), name, qty, price);
        total += price;
    }

    void printTotal() {
        StringBuilder sb = new StringBuilder();
        sb.append("%-").append(item_width).append("s %").append(qty_width).append("s %").append(price_width);
        String fs1 = sb.toString();
        f.format(fs1 + ".2f\n", "Tax", "", total*0.06);
        f.format(fs1 + "s\n", "", "", "-----");
        f.format(fs1 + ".2f\n", "Total", "", total*1.06);
    }
}
