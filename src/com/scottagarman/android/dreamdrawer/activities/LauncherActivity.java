package com.scottagarman.android.dreamdrawer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.actionbarsherlock.app.SherlockActivity;
import com.parse.ParseUser;
import com.scottagarman.android.dreamdrawer.R;
import net.simonvt.widget.MenuDrawer;
import net.simonvt.widget.MenuDrawerManager;

public class LauncherActivity extends SherlockActivity {

    private MenuDrawerManager mMenuDrawer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set content view
        mMenuDrawer = new MenuDrawerManager(this, MenuDrawer.MENU_DRAG_CONTENT);
        mMenuDrawer.setContentView(R.layout.launcher_activity);

        // Set menu view
        mMenuDrawer.setMenuView(R.layout.menu_drawer);
        mMenuDrawer.getMenuView().findViewById(R.id.menu_drawer_logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser.logOut();
                startActivity(new Intent(LauncherActivity.this, LoginActivity.class));
                mMenuDrawer.closeMenu();
            }
        });

        // Set home enabled
        getActionBar().setDisplayHomeAsUpEnabled(true);

        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
        } else {
          startActivity(new Intent(LauncherActivity.this, LoginActivity.class));
        }
    }

    @Override
    public boolean onOptionsItemSelected(com.actionbarsherlock.view.MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mMenuDrawer.toggleMenu();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        final int drawerState = mMenuDrawer.getDrawerState();
        if (drawerState == MenuDrawer.STATE_OPEN || drawerState == MenuDrawer.STATE_OPENING) {
            mMenuDrawer.closeMenu();
            return;
        }

        super.onBackPressed();
    }
}
