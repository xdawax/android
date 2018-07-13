package com.example.dawa1.thirty;

import android.support.v4.app.Fragment;
import android.os.Bundle;

public class GameActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new GameFragment();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.dawa1.thirty.R.layout.activity_fragment);

        createFragment();

    }
}
