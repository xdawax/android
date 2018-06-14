package com.example.dawa1.changecolors;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChangeColors extends AppCompatActivity {

    Button mBlackWhiteButton;
    Button mWhiteBlackButton;
    Button mBlueRedButton;
    Button mGreenBlueButton;

    public static final String EXTRA_TEXT_COLOR =
            "com.example.dawa1.changecolors.text_color";
    public static final String EXTRA_BACK_COLOR =
            "com.example.dawa1.changecolors.back_color";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_colors);

        mBlackWhiteButton = (Button) findViewById(R.id.black_whiteButton);
        mBlackWhiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mWhiteBlackButton = (Button) findViewById(R.id.white_blackButton);
        mWhiteBlackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mBlueRedButton = (Button) findViewById(R.id.blue_redButton);
        mBlueRedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mGreenBlueButton = (Button) findViewById(R.id.green_blueButton);
        mGreenBlueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
