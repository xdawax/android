package com.example.dawa1.thirty;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private int mDiceAmount = 6;
    private int mMaxTries = 3;

    private ImageView mDieOne;
    private ImageView mDieTwo;
    private ImageView mDieThree;
    private ImageView mDieFour;
    private ImageView mDieFive;
    private ImageView mDieSix;

    private Button mRollButton;
    private Button mSkipButton;

    private Spinner mModeSpinner;

    private ArrayList<ImageView> mImageViewDice = new ArrayList<ImageView>();
    private ArrayList<Die> mDice = new ArrayList<Die>();

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_game);
/*
        createDice();
        setImageViews();
        setImageViewListeners();
        setButtons();
  */
    }
/*
    private void createDice() {

        for (int i = 0; i < mDiceAmount; i++) {
            mDice.add(i, new Die());
        }
    }

    private void rollDice() {

        int dieValue;
        Die currentDie;

        for (int i = 0; i < mDiceAmount; i++) {
            currentDie = mDice.get(i);

            if(!currentDie.isLocked()) {
                currentDie.roll();
                dieValue = mDice.get(i).getValue();
                Log.i("RollDice", ("" + dieValue));
                setDieWhite(i, dieValue);
            }
        }

        mMaxTries--;
    }

    private void setDieWhite(int dieIndex, int dieValue) {
        mImageViewDice.get(dieIndex).setImageResource(mWhiteDice[dieValue-1]);
    }

    private void setDieGrey(int dieIndex, int dieValue) {
        mImageViewDice.get(dieIndex).setImageResource(mGreyDice[dieValue-1]);
    }

    private int getRandom(int range) {
        Random rnd = new Random();
        return rnd.nextInt(range) + 1;
    }

    private void setImageViews() {
       mImageViewDice.add(0, mDieOne = (ImageView) findViewById(R.id.die_one));
       mImageViewDice.add(1, mDieTwo = (ImageView) findViewById(R.id.die_two));
       mImageViewDice.add(2, mDieThree = (ImageView) findViewById(R.id.die_three));
       mImageViewDice.add(3, mDieFour = (ImageView) findViewById(R.id.die_four));
       mImageViewDice.add(4, mDieFive = (ImageView) findViewById(R.id.die_five));
       mImageViewDice.add(5, mDieSix = (ImageView) findViewById(R.id.die_six));

        for (int i = 0; i < mDiceAmount; i++) {
            mDice.get(i).roll();
            setDieWhite(i, mDice.get(i).getValue());
        }

    }

    private void setImageViewListeners() {
        for (int i = 0; i < mImageViewDice.size(); i++) {
            final int index = i;
            mImageViewDice.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    handleDieLock(index);
                }
            });
        }
    }

    private void handleDieLock(int dieIndex) {
        Die currentDie = mDice.get(dieIndex);
        int dieValue = currentDie.getValue();

        if (currentDie.isLocked()) {
            currentDie.unLock();
            setDieWhite(dieIndex, dieValue);
        } else {
            currentDie.lock();
            setDieGrey(dieIndex, dieValue);
        }
    }

    private void setButtons() {
        mRollButton = (Button) findViewById(R.id.roll_button);
        mRollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mMaxTries > 0) {
                    rollDice();
                }
            }
        });

        mSkipButton = (Button) findViewById(R.id.skip_roll_button);
        mSkipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Gör en knapp för att bekräfta
                mMaxTries = 0;
            }
        });
    }
*/
}
