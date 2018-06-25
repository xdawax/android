package com.example.dawa1.displayapiversion;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.awt.font.TextAttribute;

public class MainActivity extends AppCompatActivity {

    private Button mCheckAPIButton;
    private TextView mDisplayAPITextView;

    private int mVersionCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mVersionCode = Build.VERSION.SDK_INT;

        mDisplayAPITextView = (TextView) findViewById(R.id.apiVersion_textView);

        mCheckAPIButton = (Button) findViewById(R.id.checkAPI_button);
        mCheckAPIButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDisplayAPITextView.setText(("API Version: " + mVersionCode));
            }
        });
    }
}
