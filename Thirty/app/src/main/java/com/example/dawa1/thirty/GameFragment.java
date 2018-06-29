package com.example.dawa1.thirty;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;

public class GameFragment extends Fragment {




    private enum DIE_COLOR {WHITE, GRAY, RED};
    private final int DICE_AMOUNT = 6;

    private ImageView mDieOne;
    private ImageView mDieTwo;
    private ImageView mDieThree;
    private ImageView mDieFour;
    private ImageView mDieFive;
    private ImageView mDieSix;

    private Button mRollButton;
    private Button mSkipButton;

    private Spinner mSpinner;
    private String[] mSpinnerContents;

    private ArrayList<ImageView> mDiceImageViewList;

    private Dice mDice;

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
        mDice = Dice.get(DICE_AMOUNT);

        setImageViews(v);
        setImageViewListeners(v);
        setButtons(v);
        setSpinners(v);

        return v;
    }

    private void setButtons(View v) {
        mRollButton = (Button) v.findViewById(R.id.roll_button);
        mRollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    ThirtyGameLogic.rollDice();
                    updateDice();
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

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(v.getContext(), R.array.spinner_selections,
                        R.layout.spinner_list_item);

        mSpinner.setAdapter(adapter);
    }

    private void updateDice() {
        for (int i = 0; i < DICE_AMOUNT; i++) {
            if (mDice.isUnLocked(i)) {
                setDieWhite(i, mDice.getDieValue(i));
            }
            else {
                setDieGrey(i, mDice.getDieValue(i));
            }
        }
    }

    public void setDieWhite(int dieIndex, int dieValue) {
        mDiceImageViewList.get(dieIndex).setImageResource(mWhiteDice[dieValue-1]);
    }

    public void setDieGrey(int dieIndex, int dieValue) {
        mDiceImageViewList.get(dieIndex).setImageResource(mGreyDice[dieValue-1]);
    }

    public void setDieRed(int dieIndex, int dieValue) {
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
                    // hantera om man valt att låsa tärningens värde
                }
            });
        }
    }
}
