package com.example.dawa1.intentnewview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        handleIntent();
    }

    private void handleIntent() {

        Bundle extras = getIntent().getExtras();

        if (extras == null) return;

        String msgOne = extras.getString(Intent.EXTRA_TEXT);



    }
}
