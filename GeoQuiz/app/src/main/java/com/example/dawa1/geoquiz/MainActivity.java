package com.example.dawa1.geoquiz;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mTrueButton; // Kodkonvention att attribut börjar med m
    private Button mFalseButton;
    private Button mCheatButton;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;

    private TextView mQuestionTextView;

    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true)
    };

    public static final String TAG = "MainActivity";
    public static final String KEY_INDEX = "index";
    public static final String CHEATED_INDEX = "cheated index";
    public static final String ANSWER = "answer";

    private int mCurrentIndex = 0;
    private int mCheatedIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_main);

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });

        mPrevButton = (ImageButton) findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decrementCurrentIndex();
                updateQuestion();
            }
        });

        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incrementCurrentIndex();
                updateQuestion();
            }
        });

        mCheatButton = (Button) findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, CheatScreen.class);
                i.putExtra(KEY_INDEX, getCurrentIndex());
                i.putExtra(ANSWER, mQuestionBank[getCurrentIndex()].isAnswerTrue());
                startActivityForResult(i, 0);
            }
        });

        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incrementCurrentIndex();
                updateQuestion();
            }
        });

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        updateQuestion();
        logDisplayInfo();


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("onActivityResult", "Entered");
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                if (data == null) {
                    return;
                }
                Log.i("onActivityResult", ("" + mCheatedIndex));
                mCheatedIndex = data.getIntExtra(CheatScreen.CHEATED_INDEX, -1);
                Log.i("onActivityResult", ("" + mCheatedIndex));
            }

        }
    }

    private int getCurrentIndex() {
        return mCurrentIndex % mQuestionBank.length;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.i(TAG, "in onSaveInstanceState - Saving UI state");
        outState.putInt(KEY_INDEX, mCurrentIndex);
        outState.putInt(CHEATED_INDEX, mCheatedIndex);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    private void logDisplayInfo() {
        int displayInfo = getResources().getConfiguration().orientation;

        if (displayInfo == Configuration.ORIENTATION_LANDSCAPE) {
            Log.d("Orientation", "Landscape mode");
        } else {
            Log.d("Orientation", "Portrait mode");
        }

    }

    private void incrementCurrentIndex() {
        mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
    }

    private void decrementCurrentIndex() {

        if (mCurrentIndex == 0) {
            mCurrentIndex = mQuestionBank.length - 1;
        } else {
            mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
        }
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        int messageResId = 0;
        Log.i("checkAnswer", ("Cheated index:" + mCheatedIndex));
        if (mCheatedIndex == mCurrentIndex) {
            Toast.makeText(MainActivity.this, "Cheating is not ok!",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
        }
        else {
            messageResId = R.string.incorrect_toast;
        }

        Toast.makeText(MainActivity.this, messageResId,
                Toast.LENGTH_SHORT).show();
    }

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }
}
