package com.scottagarman.android.dreamdrawer.activities;

import android.content.Intent;
import android.os.Bundle;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.parse.ParseUser;
import com.scottagarman.android.dreamdrawer.R;

public class LauncherActivity extends MenuDrawerActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDDContentView(R.layout.activity_launcher);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Add")
            .setIcon(android.R.drawable.ic_menu_add)
            .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    return true;
                }
            })
            .setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS)
            .setVisible(true);

        menu.add("Logout")
            .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    ParseUser.logOut();
                    startActivity(new Intent(LauncherActivity.this, LoginActivity.class));
                    return true;
                }
            })
            .setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_NEVER)
            .setVisible(getParseUser() != null && getParseUser().isAuthenticated());
        return super.onCreateOptionsMenu(menu);
    }
}
