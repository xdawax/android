package com.example.dawa1.thirty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dice {

    private static Dice sDice;
    private List<Die> mDice;
    private static boolean[] sLockedDice;
    private int size;

    public int getSize() {
        return size;
    }

    public void rollDice() {
        for (int i = 0; i < size; i++) {
            if (isUnLocked(i)) {
                rollDie(i);
            }
        }
    }

    public static Dice get() {
        if (sDice == null) {
            sDice = new Dice();
        }
        return sDice;
    }

    public void sortDiceDescending() {
        Die nextDie;
        Die currentDie;
        boolean swapped = true;

        while (swapped) {
            swapped = false;
            for (int i = 0; i < size - 1; i++) {
                currentDie = mDice.get(i);
                nextDie = mDice.get(i + 1);
                if (currentDie.getValue() < nextDie.getValue()) {
                    Collections.swap(mDice, i, i+1);
                    swapped = true;
                }
            }
        }
    }



    private Dice() {
        size = 6;
        mDice = new ArrayList<>();
        sLockedDice = new boolean[size];

        for (int i = 0; i < size; i++) {
            mDice.add(new Die());
        }

        for (int i = 0; i < size; i++) {
            sLockedDice[i] = false;
        }
    }

    public void changeLock(int index) {
        if (index >= size) {
            return;
        } else {
            sLockedDice[index] = !(sLockedDice[index]);
        }
    }

    public static boolean isUnLocked(int index) {
        return !(sLockedDice[index]);
    }

    public int getDieValue(int index) {
        if (index >= size) {
            return -1;
        } else {
            return mDice.get(index).getValue();
        }
    }

    public void rollDie(int index) {
        if (index >= size) {
            return;
        } else {
            mDice.get(index).roll();
        }
    }

    public int[] getValueArray(int elements) {
        int valueArray[] = new int[elements];

        for (int i = 0; i < elements; i++) {
            valueArray[i] = getDieValue(i);
        }

        return valueArray;
    }
}
