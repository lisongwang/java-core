package com.lisong.learn.core.holding;

import java.util.PriorityQueue;
import java.util.Queue;

import static com.lisong.learn.core.util.Print.print;

public class Exercise29 {

    public static void main(String[] args) {

        Queue<NoneOrder> queue = new PriorityQueue<>();
        queue.offer(new NoneOrder());
        queue.offer(new NoneOrder());
        print(queue);
    }
}

class NoneOrder implements Comparable<NoneOrder>{
    @Override
    public int compareTo(NoneOrder o) {
        return this.hashCode() - o.hashCode();
    }
}
