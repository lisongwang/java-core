package com.lisong.learn.core.containers;

import com.lisong.learn.core.util.Generators;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

import static com.lisong.learn.core.util.Print.printnb;

public class Exercise11 {

    private static class RandomInt implements Comparable<RandomInt> {
        private static Random rand = new Random(68);
        private final Integer i = rand.nextInt(100);

        @Override
        public int compareTo(RandomInt o) {
            return i.compareTo(o.i);
        }

        @Override
        public String toString() {
            return i.toString();
        }
    }

    public static void main(String[] args) {

        Queue<RandomInt> q = Generators.fill(new PriorityQueue<>(), RandomInt::new, 15);
        while(q.peek() != null)
            printnb(q.poll() + " ");
    }
}