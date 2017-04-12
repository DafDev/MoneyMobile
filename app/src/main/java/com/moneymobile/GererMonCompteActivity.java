package com.moneymobile;

import beans.User;
import util.ActivityUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GererMonCompteActivity extends BaseActivity {

	private Button menu_accueil;
	private Button recharger_compte;
	private Button retirer_argent;
	private TextView montantGereCompteActivity;
	private Bundle newBundle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gerer_mon_compte);
		
		menu_accueil 		= (Button) findViewById(R.id.back_menu_accueilGerermoncompte);
		recharger_compte 	= (Button) findViewById(R.id.recharger_compteGererMonCompte);
		retirer_argent 		= (Button) findViewById(R.id.retirer_argentGererMonCompte);
		montantGereCompteActivity = (TextView) findViewById(R.id.montantGererMonCompte);
		newBundle = new Bundle();

		montantGereCompteActivity.setText("Solde: "+User.getSolde());
		
		menu_accueil.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				ActivityUtil.switchActivity(GererMonCompteActivity.this, AccueilActivity.class, newBundle, true);
			}
		});
		
		recharger_compte.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				ActivityUtil.switchActivity(GererMonCompteActivity.this, RechargerCompteActivity.class, newBundle, true);
			}
		});

		retirer_argent.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				ActivityUtil.switchActivity(GererMonCompteActivity.this, RetirerActivity.class, newBundle, true);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gerer_mon_compte, menu);
		return true;
	}

}
