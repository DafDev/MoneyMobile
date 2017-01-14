package com.moneymobile;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class CoopterUnAmiActivity extends BaseActivity {

	/*@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_coopter_un_ami);
	}*/

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.coopter_un_ami, menu);
		return true;
	}

}
