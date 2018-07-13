package com.example.dawa1.thirty;

import android.support.v4.app.Fragment;
import android.os.Bundle;

public class ScoreActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new ScoreFragment();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        createFragment();
    }
}
