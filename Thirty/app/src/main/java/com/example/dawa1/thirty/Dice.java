package com.example.dawa1.thirty;

import java.util.ArrayList;
import java.util.List;

public class Dice {

    private static Dice sDice;
    private List<Die> mDice;
    private int size = 6;

    public int getSize() {
        return size;
    }

    public static Dice get() {
        if (sDice == null) {
            sDice = new Dice();
        }
        return sDice;
    }

    private Dice() {
        mDice = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            mDice.add(new Die());
        }
    }

    public int getDieValue(int index) {
        if (index >= mDice.size()) {
            return -1;
        } else {
            return mDice.get(index).getValue();
        }
    }

    public void rollDie(int index) {
        if (index >= mDice.size()) {
            return;
        } else {
            mDice.get(index).roll();
        }
    }
}
