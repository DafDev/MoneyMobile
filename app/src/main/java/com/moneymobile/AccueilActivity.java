package com.moneymobile;

import util.ActivityUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import beans.User;

import com.stackmob.sdk.callback.StackMobModelCallback;
import com.stackmob.sdk.exception.StackMobException;

public class AccueilActivity extends BaseActivity {

	private User user;
	Bundle newBundle = new Bundle();
	private Button envoyer;
	private Button demander;
	private Button accepter;
	private Button gererCompte;
	private Button historique;
	private Button gererPseudo;
	private Button coopter;
	private Button aide;
	private Button deconnexion;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_accueil);
		
		
		newBundle = getIntent().getExtras();
		
		envoyer 		= (Button) findViewById(R.id.envoyer_argent); 
		demander 		=  (Button) findViewById(R.id.demander_argent);
		accepter 		=  (Button) findViewById(R.id.accepter_argent);
		gererCompte 	=  (Button) findViewById(R.id.gerer_compte);
		historique 		=  (Button) findViewById(R.id.historique);
		gererPseudo 	=  (Button) findViewById(R.id.gerer_pseudo);
		coopter 		=  (Button) findViewById(R.id.coopter_ami);
		aide 			=  (Button) findViewById(R.id.aide);
		deconnexion 	=  (Button) findViewById(R.id.deconnexion);
		
		envoyer.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ActivityUtil.switchActivity(AccueilActivity.this, EnvoyerArgentActivity.class, newBundle, true);
			}
		});
		
		demander.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ActivityUtil.switchActivity(AccueilActivity.this, DemanderArgentActivity.class, newBundle, true);
			}
		});
		
		accepter.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ActivityUtil.switchActivity(AccueilActivity.this, AccepterArgentActivity.class, newBundle, true);
			}
		});
		
		gererCompte.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ActivityUtil.switchActivity(AccueilActivity.this, GererMonCompteActivity.class, newBundle, true);
			}
		});
		
		historique.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ActivityUtil.switchActivity(AccueilActivity.this, HistoriqueActivity.class, newBundle, true);
			}
		});
		
		gererPseudo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ActivityUtil.switchActivity(AccueilActivity.this, GererMonPseudoActivity.class, newBundle, true);
			}
		});
		
		coopter.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ActivityUtil.switchActivity(AccueilActivity.this, CoopterUnAmiActivity.class, newBundle, true);
			}
		});
		
		aide.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ActivityUtil.switchActivity(AccueilActivity.this, AideActivity.class, newBundle, true);
			}
		});
		
		deconnexion.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ActivityUtil.switchActivity(AccueilActivity.this, DeconnexionActivity.class, newBundle, true);
			}
		});
		//Log.d("Password =>",newBundle.getString("password"));
		/*user = new User(newBundle.getString("login"),newBundle.getString("password"));
		user.login(new StackMobModelCallback() {
			  
		    @Override
		    public void success() {
		    	Log.d("Je suis connect� =>",user.getID());
		    }
		  
		    @Override
		    public void failure(StackMobException e) {
		    	Log.d("Erreur lors de la tentative de connexion =>",user.getID());
		    }
		});*/
		/*user.login(new StackMobModelCallback() {
		    @Override
		    public void success() {
		    	Toast.makeText(AccueilActivity.this, "Je suis connect�", Toast.LENGTH_LONG).show();
		    	Log.d("Je suis connect� =>",user.getID());
		    	//ActivityUtil.switchActivity(AccueilActivity.this, AccueilActivity.class, newBundle, true);
		    }
		  
		    @Override
		    public void failure(StackMobException e) {
		    	Toast.makeText(AccueilActivity.this, "Erreur lors de la tentative de connexion", Toast.LENGTH_LONG).show();
		    	Log.d("Erreur lors de la tentative de connexion =>",user.getID());
		    	
		    }
		});*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.accueil, menu);
		return true;
	}

}
