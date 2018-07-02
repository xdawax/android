package com.example.dawa1.thirty;

public class ThirtyGameLogic {

    private static Dice mDice = Dice.get(6);
    private static int rollsLeft = 3;

    public static void rollDice() {
        int size = mDice.getSize();

        for (int i = 0; i < size; i++) {
            if (mDice.isUnLocked(i)) {
                if (rollsLeft > 0) {
                    if (mDice.isUnLocked(i)) {
                        mDice.rollDie(i);
                    }
                }
            }
        }

        rollsLeft--;
    }

    public void caluclateScore() {

    }

    // change to private after debug
    public static void resetRolls() {
        rollsLeft = 3;
    }

}
