package com.scottagarman.android.dreamdrawer;

import android.app.Activity;
import android.os.Bundle;
import net.simonvt.widget.MenuDrawer;
import net.simonvt.widget.MenuDrawerManager;

public class LauncherActivity extends Activity {

    private MenuDrawerManager mMenuDrawer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set content view
        mMenuDrawer = new MenuDrawerManager(this, MenuDrawer.MENU_DRAG_CONTENT);
        mMenuDrawer.setContentView(R.layout.launcher_activity);

        // Set menu view
        mMenuDrawer.setMenuView(R.layout.menu_drawer);
    }
}
