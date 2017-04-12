package com.moneymobile;

import util.ActivityUtil;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RetirerActivity extends Activity {

	private Button retirer_20Euro;
	private Button retirer_40Euro;
	private Button retirer_60Euro;
	private Button retirer_80Euro;
	private Button retirer_100Euro;
	private Button menu_accueil;
	private Button gerer_compte;
	private Button retirerplusButton;
	private EditText retirerplus;
	private Bundle newBundle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_retirer);

		retirer_20Euro			= (Button) findViewById(R.id.retirer_20euro);
		retirer_40Euro			= (Button) findViewById(R.id.retirer_40euro);
		retirer_60Euro			= (Button) findViewById(R.id.retirer_60euro);
		retirer_80Euro			= (Button) findViewById(R.id.retirer_80euro);
		retirer_100Euro		= (Button) findViewById(R.id.retirer_100euro);
		gerer_compte			= (Button) findViewById(R.id.gerer_compte);
		menu_accueil 			= (Button) findViewById(R.id.back_menu_accueil);
		retirerplusButton		= (Button) findViewById(R.id.retirerplusButton);
		retirerplus				= (EditText) findViewById(R.id.retirerplusEditText);
		newBundle = new Bundle();

		retirer_20Euro.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				newBundle.putInt("retirer", 20);
				ActivityUtil.switchActivity(RetirerActivity.this, RetirerConfirmationActivity.class, newBundle, true);

			}
		});

		retirer_40Euro.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				newBundle.putInt("retirer", 40);
				ActivityUtil.switchActivity(RetirerActivity.this, RetirerConfirmationActivity.class, newBundle, true);
			}
		});

		retirer_60Euro.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				newBundle.putInt("retirer", 60);
				ActivityUtil.switchActivity(RetirerActivity.this, RetirerConfirmationActivity.class, newBundle, true);

			}
		});

		retirer_80Euro.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				newBundle.putInt("retirer", 80);
				ActivityUtil.switchActivity(RetirerActivity.this, RetirerConfirmationActivity.class, newBundle, true);

			}
		});

		retirer_100Euro.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				newBundle.putInt("retirer", 100);
				ActivityUtil.switchActivity(RetirerActivity.this, RetirerConfirmationActivity.class, newBundle, true);

			}
		});

		menu_accueil.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				ActivityUtil.switchActivity(RetirerActivity.this, AccueilActivity.class, newBundle, true);

			}
		});

		gerer_compte.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				ActivityUtil.switchActivity(RetirerActivity.this, GererMonCompteActivity.class, newBundle, true);

			}
		});

		retirerplusButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(retirerplus.getText().toString() != "") {
					newBundle.putInt("retirer", Integer.parseInt(retirerplus.getText().toString()));
					ActivityUtil.switchActivity(RetirerActivity.this, RetirerConfirmationActivity.class, newBundle, true);
				}
				else {
					ActivityUtil.switchActivity(RetirerActivity.this, RetirerActivity.class, newBundle, true);
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.recharger_compte, menu);
		return true;
	}

}
