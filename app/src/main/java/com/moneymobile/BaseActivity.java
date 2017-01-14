package com.moneymobile;

import com.stackmob.android.sdk.common.StackMobAndroid;
import com.stackmob.sdk.api.StackMob;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class BaseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (StackMob.getStackMob() == null) {
		      StackMobAndroid.init(getApplicationContext(), 0, "dcc7ae05-fc2c-4d2c-a5fe-a7c36bcd75c0");
		    }
	}

}
