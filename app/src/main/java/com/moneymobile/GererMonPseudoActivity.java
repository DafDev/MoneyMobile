package com.moneymobile;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class GererMonPseudoActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gerer_mon_pseudo);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gerer_mon_pseudo, menu);
		return true;
	}

}
