package com.example.dawa1.thirty;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
/*
    private int mDiceAmount = 6;
    private int mMaxTries = 3;





    private Spinner mModeSpinner;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_game);

    }

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





    private int getRandom(int range) {
        Random rnd = new Random();
        return rnd.nextInt(range) + 1;
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

*/

}
