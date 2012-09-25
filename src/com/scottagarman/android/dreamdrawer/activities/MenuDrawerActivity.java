package com.scottagarman.android.dreamdrawer.activities;

import android.os.Bundle;
import android.view.View;
import com.scottagarman.android.dreamdrawer.R;
import net.simonvt.widget.MenuDrawer;
import net.simonvt.widget.MenuDrawerManager;

public class MenuDrawerActivity extends DreamSuperActivity {
    protected MenuDrawerManager mMenuDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set content view
        mMenuDrawer = new MenuDrawerManager(this, MenuDrawer.MENU_DRAG_CONTENT);
        //mMenuDrawer.setContentView(R.layout.activity_launcher);

        // Set menu view
        mMenuDrawer.setMenuView(R.layout.menu_drawer);
        mMenuDrawer.getMenuView().findViewById(R.id.menu_drawer_logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMenuDrawer.closeMenu();
            }
        });

        // Set home enabled
        getActionBar().setDisplayHomeAsUpEnabled(true);
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

    protected void setDDContentView(int layoutId) {
        mMenuDrawer.setContentView(layoutId);
    }
}
