package com.example.dawa1.changecolors;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    private View mView;
    private Button mChangeColorsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.textView);
        mView = (View) findViewById(R.id.myView);
        mChangeColorsButton = (Button) findViewById(R.id.change_colors_button);

        mChangeColorsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ChangeColors.class);
                startActivityForResult(i, 0);
            }
        });

    }
}
