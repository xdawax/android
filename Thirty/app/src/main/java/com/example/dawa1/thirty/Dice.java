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

    /**
     * Rolls any die contained in the List<Die>
     *
     * @return      void
     */
    public void rollDice() {
        for (int i = 0; i < size; i++) {
            if (isUnLocked(i)) {
                rollDie(i);
            }
        }
    }

    /**
     * Returns the singleton Dice, if Dice has not been created a new Dice is instantiated
     *
     * @return      The singleton Dice
     */
    public static Dice get() {
        if (sDice == null) {
            sDice = new Dice();
        }
        return sDice;
    }

    /**
     * Sorts the Die contained inside the Dice in descending order using bubble sort
     *
     * @return      void
     */
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

    /**
     * Sets the value of all elements in sLockedDice to false, representing a unlocked Die
     *
     * @return      void
     */
    public void unlockAllDice() {
        for (int i = 0; i < 6; i++) {
            sLockedDice[i] = false;
        }
    }

    /**
     * Switches the lock on a given Die, false -> true or true -> false
     *
     * @param  index the index of the Die to change lock on
     * @return      void
     */
    public void changeLock(int index) {
        if (index >= size) {
            return;
        } else {
            sLockedDice[index] = !(sLockedDice[index]);
        }
    }

    /**
     * Returns the unlocked status of a Die at given index
     *
     * @param  index the index of the Die to check lockstatus on
     * @return      true if the Die is unlocked, false otherwise
     */
    public static boolean isUnLocked(int index) {
        return !(sLockedDice[index]);
    }

    /**
     * Returns the face value of the Die at selected index
     *
     * @param  index the index of the Die to read the score from
     * @return       the value of the Die
     */
    public int getDieValue(int index) {
        if (index >= size) {
            return -1;
        } else {
            return mDice.get(index).getValue();
        }
    }

    /**
     * Rolls a Die at given index
     *
     * @param  index the index of the Die to roll
     * @return      void
     */
    public void rollDie(int index) {
        if (index >= size) {
            return;
        } else {
            mDice.get(index).roll();
        }
    }

    /**
     * Creates an array containing the value of the n first Dice
     *
     * @param  elements how many Dice values to add to the array, (n)
     * @return      an integer array containing the first n Dice values
     */
    public int[] getValueArray(int elements) {
        int valueArray[] = new int[elements];

        for (int i = 0; i < elements; i++) {
            valueArray[i] = getDieValue(i);
        }

        return valueArray;
    }

    /**
     * Calculates the best possible score combination and returns the total score
     *
     * @param   spinnerIndex represents the index of the selected viewholder in the spinner
     * @param   offset the difference between 0 and the lowest possible score combination
     * @return      true if the Die is unlocked, false otherwise
     */
    public int getBestScore(int spinnerIndex, int offset) {
        sortDiceDescending();

        int[] dieValues = getValueArray(size);
        int countedThisRound[] = new int[] {0,0,0,0,0,0};

        int currentValue = 0; // håller värdet på de tärningar som för tillfället adderas
        int countValue = spinnerIndex + offset;  // Sätter t.ex index 9 -> 12 som motsvarar vad tärningarnas värde ska ha
        int totalValue = 0;

        // om man valt low representerar det spinnerIndex = 0 -> countValue = 0 + offset;
        if (countValue == offset) {
            for (int i = 0; i < size; i++) {
                totalValue += dieValues[i];
            }
            return totalValue;
        } else {
            for (int i = 0; i < size; i++) {
                // första tärningen som ska räknas
                currentValue = dieValues[i];
                // om sökt värde hittas på första tärningen addera och gå till nästa tärning
                if (currentValue == countValue) {
                    totalValue += dieValues[i];
                    dieValues[i] = 0;
                } else if (currentValue == 0) {
                    // om vi redan räknat tärningen gå vidare till nästa
                    continue;
                }
                for (int j = i + 1; j < size; j++) {
                    // om tärningen redan räknats hoppa till nästa
                    if (dieValues[j] == 0) {
                        continue;
                    } else if (dieValues[j] + currentValue < countValue) {
                        // om tärningarnas kombinerade värde är minde än det sökta addera och kolla nästa
                        currentValue += dieValues[j];
                        // spara värdet av tärningen vi räknat och nollställ det värdet som togs
                        countedThisRound[j] = dieValues[j];
                        dieValues[j] = 0;
                        continue;
                    } else if (dieValues[j] + currentValue == countValue) {
                        // om de kombinerade tärningarna matchar önskat värde nollställ dom
                        totalValue += dieValues[j] + currentValue;
                        dieValues[i] = 0;
                        dieValues[j] = 0;

                        break;
                    }
                }
                // återställ räknade tärningar om vi inte kunde hitta önskat värde samt återställ de tärningar som räknats
                for (int l = 0; l < size; l++) {
                    if (countedThisRound[l] != 0) {
                        dieValues[l] = countedThisRound[l];
                        countedThisRound[l] = 0;
                    }
                }

            }
            return totalValue;
        }
    }
}
