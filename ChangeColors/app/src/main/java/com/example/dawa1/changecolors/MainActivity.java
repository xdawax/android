package com.example.dawa1.changecolors;

import android.content.Intent;
import android.graphics.Color;
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

    //@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                if (data == null) {
                    return;
                }

                int textColor = data.getIntExtra(ChangeColors.EXTRA_TEXT_COLOR, Color.BLACK);
                int backColor = data.getIntExtra(ChangeColors.EXTRA_BACK_COLOR, Color.BLACK);

                mTextView.setTextColor(textColor);
                mView.setBackgroundColor(backColor);
            }
        }
    }
}
