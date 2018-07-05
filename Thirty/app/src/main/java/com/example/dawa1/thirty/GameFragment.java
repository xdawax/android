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
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GameFragment extends Fragment {

    private final int DICE_AMOUNT = 6;
    private final int MAX_ROLLS = 3;
    private final int SPINNER_INDEX_OFFSET = 3;
    private final int SPINNER_SIZE = 9;
    private final boolean DEBUG_MODE = false;

    private final String SPINNER_INDEX = "mSpinnerIndex";
    private final String ROLLS_LEFT = "mRollsLeft";
    private final String SCORE_BOARD = "mScoreList";

    private int mRollsLeft = MAX_ROLLS;  // saved

    private ImageView mDieOne;
    private ImageView mDieTwo;
    private ImageView mDieThree;
    private ImageView mDieFour;
    private ImageView mDieFive;
    private ImageView mDieSix;

    private Button mRollButton;
    private Button mSkipButton;
    private Button mCalculateScoreButton;

    private Spinner mSpinner;  // does not need saving

    private ArrayList<ImageView> mDiceImageViewList;  // does not need saving
    private ArrayList<Integer> mScoreList;  // saved
    private Dice mDice; // does not need saving

    private int mSpinnerIndex = 0; // saved

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

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putInt(ROLLS_LEFT, mRollsLeft);
        outState.putInt(SPINNER_INDEX, mSpinnerIndex);
        outState.putIntegerArrayList(SCORE_BOARD, mScoreList);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_game, container, false);

        if (savedInstanceState != null) {
            mRollsLeft = savedInstanceState.getInt(ROLLS_LEFT, 0);
            mSpinnerIndex = savedInstanceState.getInt(SPINNER_INDEX, 0);
            mScoreList = savedInstanceState.getIntegerArrayList(SCORE_BOARD);
        }

        mDiceImageViewList = new ArrayList<ImageView>();
        mDice = Dice.get();

        if (mScoreList == null) {
            Toast.makeText(getActivity(), "BLUBBA", Toast.LENGTH_LONG).show();
            mScoreList = new ArrayList<>(Collections.nCopies(SPINNER_SIZE + 1, 0));
        }

        setImageViews(v);
        setImageViewListeners(v);
        setButtons(v);
        updateButtons();
        setSpinners(v);

        return v;
    }

    private void setButtons(View v) {
        mRollButton = (Button) v.findViewById(R.id.roll_button);
        mRollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rollsLeft()) {
                    mDice.rollDice();
                    mRollsLeft--;
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
                // Ej implementerat
            }
        });

        mCalculateScoreButton = (Button) v.findViewById(R.id.calculate_score);
        mCalculateScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateScore();
                nextRound();
            }
        });
    }





    private void updateButtons() {
        if (rollsLeft()) {
            if (mRollsLeft > 2) {
                Toast.makeText(getContext(), mRollsLeft + ((mRollsLeft > 1) ? " rolls left!" : " roll left!"), Toast.LENGTH_SHORT).show();
            }
            mRollButton.setEnabled(true);
            mSkipButton.setEnabled(true);
            mCalculateScoreButton.setEnabled(false);
        }
        if (!rollsLeft()){
            Toast.makeText(getContext(), R.string.no_more_rolls, Toast.LENGTH_SHORT).show();
            mRollButton.setEnabled(false);
            mSkipButton.setEnabled(false);
            mCalculateScoreButton.setEnabled(true);
        }
    }

    private void calculateScore() {
        int calculatedScore = mDice.getBestScore(mSpinnerIndex, SPINNER_INDEX_OFFSET);
        mScoreList.set(mSpinnerIndex, calculatedScore);
        Toast.makeText(getActivity(),
                "Adding " + calculatedScore + " to index: " + mSpinnerIndex,
                Toast.LENGTH_SHORT).show();
    }

    private boolean rollsLeft() {
        return mRollsLeft > 0;
    }

    private void nextRound() {
        resetRolls();
        updateBoard();
    }

    private void resetRolls() {
        mDice.unlockAllDice();
        mRollsLeft = MAX_ROLLS;
        updateBoard();
    }

    private void updateBoard() {
        updateDice();
        updateButtons();
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

    // gör egen klass!
    public void setSpinners(View v) {
        mSpinner = (Spinner) v.findViewById(R.id.selectScore_spinner);
        String[] spinnerSelections = getResources().getStringArray(R.array.spinner_selections);;

        List<String> spinnerList = new ArrayList<String>(Arrays.asList(spinnerSelections));

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(v.getContext(), R.layout.spinner_list_item, spinnerList) {

            @Override
            public boolean isEnabled(int position) {
                if (mScoreList.get(position) == 0) {
                    return true;
                } else {
                    return false;
                }
            }

            // Inspiration från https://android--examples.blogspot.com/2016/10/android-change-spinner-selected-item.html
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent){
                TextView currentView = (TextView) super.getDropDownView(position, convertView, parent);
                if (!isEnabled(position)) {
                    currentView.setTextColor(Color.RED);
                } else {
                    currentView.setTextColor(Color.GREEN);
                }
                return currentView;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView currentView = (TextView) super.getView(position, convertView, parent);
                currentView.setTextColor(Color.GREEN);
                return currentView;
            }
        };

        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mSpinnerIndex = adapterView.getSelectedItemPosition();
                if (DEBUG_MODE) {
                    Toast.makeText(getActivity(), "Selected index: " + mSpinnerIndex,
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Nothing for now
            }
        });
    }

}
