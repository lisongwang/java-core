package com.lisong.learn.core.enumerated.util;

import com.lisong.learn.core.enumerated.enums.Category;
import com.lisong.learn.core.enumerated.enums.Input;
import com.lisong.learn.core.enumerated.enums.InputEnum;
import com.lisong.learn.core.io.util.TextFile;
import com.lisong.learn.core.util.Generator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.lisong.learn.core.enumerated.util.VendingMachine.State.RESTING;
import static com.lisong.learn.core.enumerated.util.VendingMachine.State.TERMINAL;
import static com.lisong.learn.core.util.Print.print;

public class VendingMachine {

    private static State state = RESTING;
    private static int amount = 0;
    private static Input select = null;
    enum StateDuration { TRANSIENT }
    enum State {
        RESTING {
            @Override
            void next(Input input) {
                Category category = input instanceof InputEnum ? Category.categorize(input) : Category.ITEM_SELECTION;
                switch(category) {
                    case MONEY:
                        amount += input.amount();
                        state = ADDING_MONEY;
                        break;
                    case SHUT_DOWN:
                        state = TERMINAL;
                    default:
                }
            }
        },
        ADDING_MONEY {
            @Override
            void next(Input input) {
                Category category = input instanceof InputEnum ? Category.categorize(input) : Category.ITEM_SELECTION;
                switch(category) {
                    case MONEY:
                        amount += input.amount();
                        break;
                    case ITEM_SELECTION:
                        select = input;
                        if(amount < select.amount())
                            print("Insufficient money for " + select);
                        else
                            state = DISPENSING;
                        break;
                    case QUIT_TRANSACTION:
                        state = GIVEN_CHANGE;
                        break;
                    case SHUT_DOWN:
                        state = TERMINAL;
                    default:
                }
            }
        },
        DISPENSING(StateDuration.TRANSIENT) {
            @Override
            void next() {
                print("here is your " + select);
                amount -= select.amount();
                select = null;
                state = GIVEN_CHANGE;
            }
        },
        GIVEN_CHANGE(StateDuration.TRANSIENT) {
            @Override
            void next() {
                if(amount > 0) {
                    print("your change " + amount);
                    amount = 0;
                }
                state = RESTING;
            }
        },
        TERMINAL {
            @Override
            void output() {
                print("Halted");
            }
        };

        void next(Input input) {
            throw new RuntimeException("Only call next(Input input) for non-transient states");
        }
        void next() {
            throw new RuntimeException("Only call next() for transient states");
        }
        void output() { print(amount); }

        private boolean isTransient = false;
        State(StateDuration duration) { isTransient = true; }
        State() {}
    }

    public static void run(Generator<Input> gen) {
        while(state != TERMINAL) {
            state.next(gen.next());
            while(state.isTransient)
                state.next();
            state.output();
        }
    }

    public static List<VendingItem> items = new ArrayList<>();
    public static void init(String fileName) throws IOException {
        List<String> list = new TextFile(fileName, ";");
        Matcher m = Pattern.compile("(\\w+)\\((\\d+)\\)").matcher("");
        for(String s : list) {
            m.reset(s);
            if(m.find()) {
                items.add(new VendingItem(m.group(1),Integer.parseInt(m.group(2))));
            }
        }
    }
}

class VendingItem implements Input {

    private final String name;
    private final int amount;

    VendingItem(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    @Override
    public int amount() {
        return amount;
    }

    @Override
    public String toString() {
        return name;
    }
}