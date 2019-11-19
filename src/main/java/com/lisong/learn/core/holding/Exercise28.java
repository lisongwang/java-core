package com.lisong.learn.core.holding;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

import static com.lisong.learn.core.util.Print.print;

public class Exercise28 {

    public static void main(String[] args) {

        Random rand = new Random();
        Queue<Double> doubleQ = new PriorityQueue<>();
        for(int i = 0; i < 20; i++) {
            doubleQ.offer(rand.nextDouble());
        }

        while(doubleQ.peek() != null) {
            print(doubleQ.poll());
        }
        print(doubleQ);
    }
}
