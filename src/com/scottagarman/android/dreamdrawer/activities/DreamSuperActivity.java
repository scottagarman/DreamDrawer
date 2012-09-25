package com.scottagarman.android.dreamdrawer.activities;

import android.content.Intent;
import android.os.Bundle;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.parse.ParseUser;
import com.scottagarman.android.dreamdrawer.R;

public class DreamSuperActivity extends SherlockActivity {
    private MenuItem mLoadingMenuItem;
    private ParseUser mParseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mParseUser = ParseUser.getCurrentUser();
        if (mParseUser != null || this instanceof LoginActivity) {
        } else {
          startActivity(new Intent(DreamSuperActivity.this, LoginActivity.class));
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

    public ParseUser getParseUser() {
        return mParseUser;
    }
}
