package com.moneymobile;

import util.ActivityUtil;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RechargerCompteActivity extends Activity {

	private Button recharge_20Euro;
	private Button recharge_40Euro;
	private Button recharge_60Euro;
	private Button recharge_80Euro;
	private Button recharge_100Euro;
	private Button menu_accueil;
	private Button gerer_compte;
	private Button envoyerplusbutton;
	private EditText envoyerplus;
	private Bundle newBundle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recharger_compte);
		
		recharge_20Euro			= (Button) findViewById(R.id.recharger_20euro);
		recharge_40Euro			= (Button) findViewById(R.id.recharger_40euro);
		recharge_60Euro			= (Button) findViewById(R.id.recharger_60euro);
		recharge_80Euro			= (Button) findViewById(R.id.recharger_80euro);
		recharge_100Euro		= (Button) findViewById(R.id.recharger_100euro);
		gerer_compte			= (Button) findViewById(R.id.gerer_compte);
		menu_accueil 			= (Button) findViewById(R.id.back_menu_accueil);
		envoyerplusbutton		= (Button) findViewById(R.id.envoyerplusButton);
		envoyerplus				= (EditText) findViewById(R.id.envoyerplusRechargerCompteEditText);
		newBundle = new Bundle();
		
		recharge_20Euro.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				newBundle.putInt("recharge", 20);
				ActivityUtil.switchActivity(RechargerCompteActivity.this, RechargerCompteConfirmationActivity.class, newBundle, true);
				
			}
		});
		
		recharge_40Euro.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				newBundle.putInt("recharge", 40);
				ActivityUtil.switchActivity(RechargerCompteActivity.this, RechargerCompteConfirmationActivity.class, newBundle, true);
			}
		});
		
		recharge_60Euro.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				newBundle.putInt("recharge", 60);
				ActivityUtil.switchActivity(RechargerCompteActivity.this, RechargerCompteConfirmationActivity.class, newBundle, true);
				
			}
		});
		
		recharge_80Euro.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				newBundle.putInt("recharge", 80);
				ActivityUtil.switchActivity(RechargerCompteActivity.this, RechargerCompteConfirmationActivity.class, newBundle, true);
				
			}
		});
		
		recharge_100Euro.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				newBundle.putInt("recharge", 100);
				ActivityUtil.switchActivity(RechargerCompteActivity.this, RechargerCompteConfirmationActivity.class, newBundle, true);
				
			}
		});
		
		menu_accueil.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				ActivityUtil.switchActivity(RechargerCompteActivity.this, AccueilActivity.class, newBundle, true);
				
			}
		});

		gerer_compte.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				ActivityUtil.switchActivity(RechargerCompteActivity.this, GererMonCompteActivity.class, newBundle, true);

			}
		});

		envoyerplusbutton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(envoyerplus.getText().toString() != "") {
					newBundle.putInt("recharge", Integer.parseInt(envoyerplus.getText().toString()));
					ActivityUtil.switchActivity(RechargerCompteActivity.this, RechargerCompteConfirmationActivity.class, newBundle, true);
				}
				else {
					ActivityUtil.switchActivity(RechargerCompteActivity.this, RechargerCompteActivity.class, newBundle, true);
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
