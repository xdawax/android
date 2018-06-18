package com.example.dawa1.geoquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatScreen extends AppCompatActivity {

    public static final String CHEATED_INDEX = "cheated index";

    TextView mCurrentIndex;
    TextView mAnswer;
    Button mCheatButton;
    Button mReturnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat_screen);

        mCurrentIndex = (TextView) findViewById(R.id.index);
        mAnswer = (TextView) findViewById(R.id.answer);

        mCheatButton = (Button) findViewById(R.id.cheat_button);
        mReturnButton = (Button) findViewById(R.id.return_button);

        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleIntent();

            }
        });

        mReturnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setResult(int cheatedIndex, int nothing) {
        Intent data = new Intent();
        data.putExtra(CHEATED_INDEX, cheatedIndex);
        setResult(RESULT_OK, data);
    }

    public void handleIntent() {
        Intent i = getIntent();

        boolean answer = i.getBooleanExtra(MainActivity.ANSWER, false);
        int index = i.getIntExtra(MainActivity.KEY_INDEX, -1);

        mCurrentIndex.setText(("Index: " + index));
        mAnswer.setText(("Answer: " + (answer ? "True" : "False")));

        setResult(index, 0);
    }

}
