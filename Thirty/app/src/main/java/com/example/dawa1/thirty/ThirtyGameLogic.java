package com.example.dawa1.thirty;

import java.util.ArrayList;
import java.util.List;

public class ThirtyGameLogic {

    private static boolean[] sLockedDice;

    public static void rollDice(Dice dice) {
        int size = dice.getSize();

        for (int i = 0; i < size; i++) {
            if (isUnLocked(i)) {
                dice.rollDie(i);
            }
        }
    }

    public static void changeLock(int index) {
        sLockedDice[index] = !(sLockedDice[index]);
    }

    public static boolean isUnLocked(int index) {
        return !(sLockedDice[index]);
    }

    public static void init(int diceAmount) {
        sLockedDice = new boolean[diceAmount];

        for (int i = 0; i < diceAmount; i++) {
            sLockedDice[i] = false;
        }
    }
}
