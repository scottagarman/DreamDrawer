package com.scottagarman.android.dreamdrawer;

import android.app.Application;
import com.parse.Parse;

public class DreamDrawerApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();

		Parse.initialize(this, "mkRZTxTwYfmSHK3bq0SQq7gwlZ8eN0T4eTtBP3lM", "0nyzAugX743dN0xoSFTUg5g8ljJZGOEodTEmXzVo");
	}

}