package com.moneymobile;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ConfirmerRechargeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_confirmer_recharge);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.confirmer_recharge, menu);
		return true;
	}

}
