package com.lisong.learn.core.enumerated.util;

import com.lisong.learn.core.enumerated.enums.Category;
import com.lisong.learn.core.enumerated.enums.Input;
import com.lisong.learn.core.util.Generator;

import java.util.EnumMap;

import static com.lisong.learn.core.enumerated.util.VendingMachine2.State.*;
import static com.lisong.learn.core.util.Print.print;

public class VendingMachine2 {

    private VendingMachine2.State state = RESTING;
    private int amount = 0;
    private Input select = null;
    enum StateDuration { TRANSIENT }
    enum State {
        RESTING, ADDING_MONEY, DISPENSING(StateDuration.TRANSIENT), GIVEN_CHANGE(StateDuration.TRANSIENT), TERMINAL;
        private boolean isTransient = false;
        State(VendingMachine2.StateDuration duration) { isTransient = true; }
        State() {}
    }

    interface Transfer {
        void next(Input input);
        void next();
    }

    private EnumMap<State,Transfer> stateMap = new EnumMap<>(State.class);
    {
        stateMap.put(RESTING, new Transfer() {
            @Override
            public void next(Input input) {
                switch(Category.categorize(input)) {
                    case MONEY:
                        amount += input.amount();
                        state = ADDING_MONEY;
                        break;
                    case SHUT_DOWN:
                        state = TERMINAL;
                    default:
                }
            }

            @Override
            public void next() {
                throw new RuntimeException("Only call next() for transient states");
            }
        });
        stateMap.put(ADDING_MONEY, new Transfer() {
            @Override
            public void next(Input input) {
                switch(Category.categorize(input)) {
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

            @Override
            public void next() {
                throw new RuntimeException("Only call next() for transient states");
            }
        });
        stateMap.put(DISPENSING, new Transfer() {
            @Override
            public void next(Input input) {
                throw new RuntimeException("Only call next(Input input) for non-transient states");
            }

            @Override
            public void next() {
                print("here is your " + select);
                amount -= select.amount();
                select = null;
                state = GIVEN_CHANGE;
            }
        });
        stateMap.put(GIVEN_CHANGE, new Transfer() {
            @Override
            public void next(Input input) {
                throw new RuntimeException("Only call next(Input input) for non-transient states");
            }

            @Override
            public void next() {
                if(amount > 0) {
                    print("your change " + amount);
                    amount = 0;
                }
                state = RESTING;
            }
        });
        stateMap.put(TERMINAL, new Transfer() {
            @Override
            public void next(Input input) {
                throw new RuntimeException("Do not call next(Input input) for terminal state");
            }

            @Override
            public void next() {
                throw new RuntimeException("Do not call next() for terminal state");
            }
        });
    }

    public void run(Generator<Input> gen) {
        while(state != TERMINAL) {
            stateMap.get(state).next(gen.next());
            while(state.isTransient)
                stateMap.get(state).next();
            print(amount);
        }
        print("Halted");
    }
}