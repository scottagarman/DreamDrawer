package com.scottagarman.android.dreamdrawer.activities;

import android.os.Bundle;
import com.scottagarman.android.dreamdrawer.R;

public class LauncherActivity extends DDActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDDContentView(R.layout.activity_launcher);
    }
}
