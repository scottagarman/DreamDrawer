package com.scottagarman.android.dreamdrawer;

import android.os.Bundle;
import com.actionbarsherlock.app.SherlockActivity;
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
}
