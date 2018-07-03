package com.example.dawa1.thirty;

public class ThirtyGameLogic {

    private static Dice mDice = Dice.get();




    public static int getRollsLeft() {
        return -1;
    }


    public void caluclateScore(int scoreType) {
        mDice.sortDiceDescending();
    }

    // change to private after debug
    public static void resetRolls() {
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
