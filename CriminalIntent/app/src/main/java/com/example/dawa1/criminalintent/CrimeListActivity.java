package com.example.dawa1.criminalintent;

import android.support.v4.app.Fragment;

public class CrimeListActivity extends SingleFragmentActivity {

    protected Fragment createFragment() {
        return new CrimeListFragment();
    }


}
