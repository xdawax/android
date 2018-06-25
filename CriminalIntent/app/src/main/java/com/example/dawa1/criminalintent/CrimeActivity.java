package com.example.dawa1.criminalintent;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CrimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime);

        FragmentManager fragMan = getSupportFragmentManager();
        Fragment fragment = fragMan.findFragmentById(R.id.fragmentContainer);

        if (fragMan == null) {
            fragment = new CrimeFragment();
            fragMan.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
        }
    }
}
