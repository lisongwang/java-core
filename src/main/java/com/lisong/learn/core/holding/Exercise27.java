package com.lisong.learn.core.holding;

import java.util.LinkedList;
import java.util.Queue;

import static com.lisong.learn.core.util.Print.print;

public class Exercise27 {

    public static void main(String[] args) {

        CommandConsumer cc = new CommandConsumer();
        cc.consumeCommand(CommandQueue.fillQueue());
    }
}

class Command {

    private String name;
    Command(String name) { this.name = name; }
    void operation() { print("Command " + name + " executing."); }
}

class CommandQueue {

    static Queue<Command> fillQueue() {

        Queue<Command> cq = new LinkedList<>();
        cq.offer(new Command("Start"));
        cq.offer(new Command("Prepare"));
        cq.offer(new Command("Run"));
        cq.offer(new Command("Pending"));
        cq.offer(new Command("Stop"));
        return cq;
    }
}

class CommandConsumer {

    void consumeCommand(Queue<Command> queue) {

        while(queue.peek() != null) {
            queue.poll().operation();
        }
    }
}
