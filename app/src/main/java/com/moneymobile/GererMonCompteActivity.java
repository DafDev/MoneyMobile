package com.moneymobile;

import util.ActivityUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class GererMonCompteActivity extends BaseActivity {

	//private Button valider;
	private Button menu_accueil;
	private Button recharger_compte;
	private Button virer_argent;
	private Button deconnexion;
	private Bundle newBundle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gerer_mon_compte);
		
		menu_accueil 		= (Button) findViewById(R.id.back_menu_accueil);
		//valider 			= (Button) findViewById(R.id.valider); 
		recharger_compte 	= (Button) findViewById(R.id.recharger_compte); 
		virer_argent 		= (Button) findViewById(R.id.virer_argent); 
		deconnexion			= (Button) findViewById(R.id.deconnexion); 
		newBundle = new Bundle();
		
		menu_accueil.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				ActivityUtil.switchActivity(GererMonCompteActivity.this, AccueilActivity.class, newBundle, true);
				
			}
		});
		
		/*valider.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
		});*/
		
		recharger_compte.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				ActivityUtil.switchActivity(GererMonCompteActivity.this, RechargerCompteActivity.class, newBundle, true);
				
			}
		});
		
		virer_argent.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//Log.d("Clique virer","");
				ActivityUtil.switchActivity(GererMonCompteActivity.this, EnvoyerArgentActivity.class, newBundle, true);
				
			}
		});
		
		deconnexion.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				ActivityUtil.switchActivity(GererMonCompteActivity.this, DeconnexionActivity.class, newBundle, true);
				
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
