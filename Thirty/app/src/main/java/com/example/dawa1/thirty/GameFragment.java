package com.example.dawa1.thirty;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GameFragment extends Fragment {

    private final int DICE_AMOUNT = 6;
    private final int MAX_ROLLS = 3;
    private final int SPINNER_INDEX_OFFSET = 3;
    private final boolean DEBUG_MODE = true;
    /* Debuggers */
    private Button mDebuggResetRolls;
    private int mRollsLeft = MAX_ROLLS;

    private ImageView mDieOne;
    private ImageView mDieTwo;
    private ImageView mDieThree;
    private ImageView mDieFour;
    private ImageView mDieFive;
    private ImageView mDieSix;

    private Button mRollButton;
    private Button mSkipButton;

    private Spinner mSpinner;

    private ArrayList<ImageView> mDiceImageViewList;

    private Dice mDice;

    private int mSpinnerIndex = 0;

    private int[] mWhiteDice = new int[]{R.drawable.white1, R.drawable.white2,
            R.drawable.white3, R.drawable.white4,
            R.drawable.white5, R.drawable.white6};

    private int[] mGreyDice = new int[]{R.drawable.grey1, R.drawable.grey2,
            R.drawable.grey3, R.drawable.grey4,
            R.drawable.grey5, R.drawable.grey6};

    private int[] mRedDice = new int[]{R.drawable.red1, R.drawable.red2,
            R.drawable.red3, R.drawable.red4,
            R.drawable.red5, R.drawable.red6};



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_game, container, false);

        mDiceImageViewList = new ArrayList<ImageView>();
        mDice = Dice.get();

        setImageViews(v);
        setImageViewListeners(v);
        setButtons(v);
        setSpinners(v);

        if (DEBUG_MODE) {
            mDebuggResetRolls = (Button) v.findViewById(R.id.reset_rolls);
            mDebuggResetRolls.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    resetRolls();
                }
            });
        }
        return v;
    }

    private void setButtons(View v) {
        mRollButton = (Button) v.findViewById(R.id.roll_button);
        mRollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rollsLeft()) {
                    mDice.rollDice();
                }
                updateBoard();
            }
        });

        mSkipButton = (Button) v.findViewById(R.id.skip_roll_button);
        mSkipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Spelaren är nöjd och vill inte rulla mera
                //Gör en knapp för att bekräfta
            }
        });
    }

    private void updateBoard() {
        updateDice();
        updateButtonText();
    }

    private void updateButtonText() {
        if (rollsLeft()) {
            mRollsLeft--;
            Toast.makeText(getContext(), mRollsLeft + " rolls left!", Toast.LENGTH_SHORT).show();
            mRollButton.setText(R.string.roll_dice);
            mSkipButton.setText(R.string.skip_rolls);
        }
        if (!rollsLeft()){
            Toast.makeText(getContext(), R.string.no_more_rolls, Toast.LENGTH_SHORT).show();
            mRollButton.setText(R.string.calculate_score);
            mRollButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getBestScore();
                }
            });
            mSkipButton.setText(R.string.confirm);
        }
    }

    public int getBestScore() {
        mDice.sortDiceDescending();
        int[] dieValues = mDice.getValueArray(DICE_AMOUNT);
        int countedThisRound[] = new int[] {0,0,0,0,0,0};

        int currentValue = 0; // håller värdet på de tärningar som för tillfället adderas
        int countValue = mSpinnerIndex + SPINNER_INDEX_OFFSET;  // Sätter t.ex index 9 -> 12 som motsvarar vad tärningarnas värde ska ha
        int totalValue = 0;
        int loopOffset = 1;

        for (int i = 0; i < DICE_AMOUNT; i++) {
            currentValue = dieValues[i];

            if (currentValue == countValue) {
                totalValue += dieValues[i];
                dieValues[i] = 0;
            } else if (currentValue == 0) {
                continue;
            }
            for (int j = i + loopOffset; j < DICE_AMOUNT; j++) {
                if (dieValues[j] == 0) {
                    if (j == 5) {
                        loopOffset = 1;
                    }
                    continue;
                } else if (dieValues[j] + currentValue < countValue) {
                    currentValue += dieValues[j];
                    countedThisRound[j] = dieValues[j];
                    dieValues[j] = 0;
                    continue;
                } else if (dieValues[j] + currentValue > countValue) {
                    // restores the removed values of the outerloops iteration
                    for (int l = 0; l < DICE_AMOUNT; l++) {
                        if (countedThisRound[l] != 0) {
                            dieValues[l] = countedThisRound[l];
                        }
                    }
                    i--;
                    loopOffset++;
                    break;
                } else if (dieValues[j] + currentValue == countValue) {
                    totalValue += dieValues[j] + currentValue;
                    dieValues[i] = 0;
                    dieValues[j] = 0;
                    loopOffset = 1;
                    for (int k = 0; k < DICE_AMOUNT; k++) {
                        countedThisRound[k] = 0;
                    }
                    break;
                }
            }
        }
        Toast.makeText(getActivity(), "" + totalValue, Toast.LENGTH_SHORT).show();
        return totalValue;
    }

    /* public int getBestScore() {

        mDice.sortDiceDescending();

        boolean usedDice[] = new boolean[] {false, false, false, false, false, false};



        for (int i = 0; i < DICE_AMOUNT; i++) {
            currentValue = mDice.getDieValue(i);

            if (currentValue == countValue) {
                totalValue += countValue;
                usedDice[i] = true;
                continue;
            }

            for (int j = i + loopOffset; j < DICE_AMOUNT; j++) {
                if (currentValue + mDice.getDieValue(j) < countValue && !usedDice[j] && !usedDice[i]) {
                    currentValue += mDice.getDieValue(j);
                    usedDice[j] = true;
                    countedThisRound[j] = true;
                    continue;
                } else if (currentValue + mDice.getDieValue(j) > countValue && !usedDice[j] && !usedDice[i]) {
                    for (int k = 0; k < DICE_AMOUNT; k++) {
                        if (countedThisRound[k]) {
                            usedDice[k] = false;
                            countedThisRound[k] = false;
                        }
                    }
                    i--;
                    loopOffset++;

                    break;
                } else if (currentValue + mDice.getDieValue(j) == countValue && !usedDice[j] && !usedDice[i]) {
                    usedDice[i] = true;
                    usedDice[j] = true;
                    totalValue += currentValue + mDice.getDieValue(j);
                    loopOffset = 1;



                    break;
                }
            }
        }




        return totalValue;
    }

*/



    private boolean rollsLeft() {
        return mRollsLeft > 0;
    }

    private void resetRolls() {
        mRollsLeft = MAX_ROLLS;
        updateButtonText();
    }

    private void setImageViews(View v) {
        mDiceImageViewList.add(0, mDieOne = (ImageView) v.findViewById(R.id.die_one));
        mDiceImageViewList.add(1, mDieTwo = (ImageView) v.findViewById(R.id.die_two));
        mDiceImageViewList.add(2, mDieThree = (ImageView) v.findViewById(R.id.die_three));
        mDiceImageViewList.add(3, mDieFour = (ImageView) v.findViewById(R.id.die_four));
        mDiceImageViewList.add(4, mDieFive = (ImageView) v.findViewById(R.id.die_five));
        mDiceImageViewList.add(5, mDieSix = (ImageView) v.findViewById(R.id.die_six));

        updateDice();
    }

    public void setSpinners(View v) {
        mSpinner = (Spinner) v.findViewById(R.id.selectScore_spinner);

        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(v.getContext(), R.array.spinner_selections,
                        R.layout.spinner_list_item);

        mSpinner.setAdapter(adapter);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mSpinnerIndex = adapterView.getSelectedItemPosition();
                Toast.makeText(getActivity(), "Selected index: " + mSpinnerIndex,
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Nothing for now
            }
        });
    }

    private void updateDice() {
        for (int i = 0; i < DICE_AMOUNT; i++) {
            if (mDice.isUnLocked(i)) {
                displayWhiteDie(i, mDice.getDieValue(i));
            }
            else {
                displayGreyDie(i, mDice.getDieValue(i));
            }
        }
    }



    private void displayLockedDie(int dieIndex) {
        mDiceImageViewList.get(dieIndex).setBackgroundColor(Color.BLACK);
        mDiceImageViewList.get(dieIndex).setPadding(2,2,2,2);
    }

    private void displayUnLockedDie(int dieIndex) {
        mDiceImageViewList.get(dieIndex).setBackgroundColor(Color.WHITE);
        mDiceImageViewList.get(dieIndex).setPadding(2,2,2,2);
    }

    private void displayWhiteDie(int dieIndex, int dieValue) {
        mDiceImageViewList.get(dieIndex).setImageResource(mWhiteDice[dieValue-1]);
    }

    private void displayGreyDie(int dieIndex, int dieValue) {
        mDiceImageViewList.get(dieIndex).setImageResource(mGreyDice[dieValue-1]);
    }

    private void displayRedDie(int dieIndex, int dieValue) {
        mDiceImageViewList.get(dieIndex).setImageResource(mRedDice[dieValue-1]);
    }

    private void setImageViewListeners(View v) {
        for (int i = 0; i < mDiceImageViewList.size(); i++) {
            final int index = i;
            mDiceImageViewList.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mDice.changeLock(index);
                    updateDice();
                }
            });
        }
    }
}
