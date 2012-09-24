package com.scottagarman.android.dreamdrawer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.parse.ParseUser;
import com.scottagarman.android.dreamdrawer.R;
import net.simonvt.widget.MenuDrawer;
import net.simonvt.widget.MenuDrawerManager;

public class DDActivity extends SherlockActivity {
    private MenuItem mLoadingMenuItem;
    protected MenuDrawerManager mMenuDrawer;
    protected ParseUser mParseUser;

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
                ParseUser.logOut();
                startActivity(new Intent(DDActivity.this, LoginActivity.class));
                mMenuDrawer.closeMenu();
            }
        });

        // Set home enabled
        getActionBar().setDisplayHomeAsUpEnabled(true);

        mParseUser = ParseUser.getCurrentUser();
        if (mParseUser != null || this instanceof LoginActivity) {
        } else {
          startActivity(new Intent(DDActivity.this, LoginActivity.class));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mLoadingMenuItem = menu.add("Loading...")
            //.setIcon(android.R.drawable.ic_menu_)
            .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    return true;
                }
            })
            .setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS)
            .setVisible(false);

        return super.onCreateOptionsMenu(menu);
    }

    protected void setLoading(boolean isLoading) {
        if(isLoading) {
            mLoadingMenuItem.setActionView(R.layout.layout_loading_indicator);
            mLoadingMenuItem.setVisible(true);
        }else {
            mLoadingMenuItem.setActionView(null);
            mLoadingMenuItem.setVisible(false);
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

    protected void setDDContentView(int layoutId) {
        mMenuDrawer.setContentView(layoutId);
    }
}
