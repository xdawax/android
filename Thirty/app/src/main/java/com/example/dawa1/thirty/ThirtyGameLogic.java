package com.example.dawa1.thirty;

public class ThirtyGameLogic {

    private static Dice mDice = Dice.get(6);
    private static int sRollsLeft = 3;

    public static void rollDice() {
        int size = mDice.getSize();

        for (int i = 0; i < size; i++) {
            if (mDice.isUnLocked(i)) {
                if (sRollsLeft > 0) {
                    if (mDice.isUnLocked(i)) {
                        mDice.rollDie(i);
                    }
                }
            }
        }
        sRollsLeft--;

    }

    public static int getRollsLeft() {
        return sRollsLeft;
    }

    public void caluclateScore(int scoreType) {
        mDice.sortDiceDescending();
    }

    // change to private after debug
    public static void resetRolls() {
        sRollsLeft = 3;
    }

    public static int calculateScore(int scoreMode) {
        return -1;
    }

    private static int calculateScoreLow() {
        int score = 0;

        for (int i = 0; i < mDice.getSize(); i++) {
            if (mDice.getDieValue(i) <= 3) {
                score += mDice.getDieValue(i);
            }
        }
        return score;
    }

}
