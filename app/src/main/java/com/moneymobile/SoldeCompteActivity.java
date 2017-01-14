package com.moneymobile;

import util.ActivityUtil;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SoldeCompteActivity extends Activity {

	private TextView message_accueil;
	private Button menu_accueil;
	private Button deconnexion;
	private Bundle newBundle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_solde_compte);
		
		menu_accueil 		= (Button) findViewById(R.id.back_menu_accueil);
		message_accueil		= (TextView) findViewById(R.id.message_accueil);
		deconnexion			= (Button) findViewById(R.id.deconnexion);
		newBundle		 	= getIntent().getExtras(); 
		
		message_accueil.setText("Nouveau solde du compte : "+newBundle.getString("currentSolde")+" Euro(s)");
		
		menu_accueil.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				ActivityUtil.switchActivity(SoldeCompteActivity.this, AccueilActivity.class, newBundle, true);
				
			}
		});
		
		deconnexion.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				ActivityUtil.switchActivity(SoldeCompteActivity.this, DeconnexionActivity.class, newBundle, true);
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.solde_compte, menu);
		return true;
	}

}
