package com.example.dawa1.thirty;

public class ThirtyGameLogic {

    public static void rollDice(Dice dice) {

        int size = dice.getSize();

        for (int i = 0; i < size; i++) {
            dice.rollDie(i);
        }
    }
}
