package com.moneymobile;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import util.ActivityUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import beans.Banque;
import beans.Compte;
import beans.User;

import com.stackmob.sdk.api.StackMobOptions;
import com.stackmob.sdk.callback.StackMobModelCallback;
import com.stackmob.sdk.exception.StackMobException;

public class InscriptionActivity extends BaseActivity {

	private Banque banqueDepot;
	private User user;
	private Bundle newBundle;
	private Button mValider;
	private EditText mUsername;
	private EditText mPassword;
	private EditText mTelephone;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inscription);
		
		mValider = (Button) findViewById(R.id.valider); 
		mUsername = (EditText) findViewById(R.id.usernameEditText); 
		mPassword = (EditText) findViewById(R.id.passwordEditText); 
		mTelephone = (EditText) findViewById(R.id.telephone);
		
		newBundle = new Bundle();
		banqueDepot = new Banque();
		
		mValider.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				inscriptionUser();
				//ActivityUtil.switchActivity(InscriptionActivity.this, ConnexionActivity.class, newBundle, true);
				ActivityUtil.switchActivity(InscriptionActivity.this, AccueilActivity.class, newBundle, true);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.inscription, menu);
		return true;
	}
	
	public void inscriptionUser(){
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		
		/*------------- Creation du compte utilisateur ------------*/
		
		Compte compte = new Compte("Compte Mobile","0",dateFormat.format(date));
		user = new User(mUsername.getText().toString(),mUsername.getText().toString());
		user.setPhone(mTelephone.getText().toString());
		user.setCompteRelation(compte);
		
		banqueDepot.setSoldeCompte("10");
		//banqueDepot.setUserRelation(new User(mUsername.getText().toString(),mUsername.getText().toString()));
		//banqueDepot.getUserRelation().setPhone(mTelephone.getText().toString());
		//banqueDepot.getUserRelation().getCompteRelation().setTitreCompte("Compte Mobile");
		//banqueDepot.getUserRelation().getCompteRelation().setSolde("0");
		//banqueDepot.getUserRelation().getCompteRelation().setDateDernierMouvement(dateFormat.format(date));
		//user = new User(mUsername.getText().toString(),mUsername.getText().toString());
		//user.setPhone(mTelephone.getText().toString());
		//user.getCompteRelation().setTitreCompte("Compte Mobile");
		//user.getCompteRelation().setSolde("0");
		//user.getCompteRelation().setDateDernierMouvement(dateFormat.format(date)); 
		banqueDepot.setUserRelation(user);
		banqueDepot.save(StackMobOptions.depthOf(2));/*,new StackMobModelCallback() {
			@Override
			public void success() {
				Toast.makeText(InscriptionActivity.this, "Inscription réussie", Toast.LENGTH_LONG).show();
				//ActivityUtil.switchActivity(InscriptionActivity.this, AccueilActivity.class, newBundle, true);
				
			}

			@Override
			public void failure(StackMobException arg0) {
				Toast.makeText(InscriptionActivity.this, "inscriptin échouée", Toast.LENGTH_LONG).show();
				
			}
			
		});*/
		
		banqueDepot.getUserRelation().login(new StackMobModelCallback() { // StackMobOptions.depthOf(1),
		    @Override
		    public void success() {
		    	Log.d("Connexion réussie =>",banqueDepot.getUserRelation().getID());
		    	//banqueDepot = new Banque("400",user.getID());
		    	ActivityUtil.switchActivity(InscriptionActivity.this, AccueilActivity.class, newBundle, true);
		    }
		    @Override
		    public void failure(StackMobException e) {
		    	Log.d("Echec de la connexion",banqueDepot.getUserRelation().getID()+" + "+mPassword.getText().toString());
		    }
		});
		/*user.save(new StackMobModelCallback(){

			@Override
			public void success() {
				Toast.makeText(InscriptionActivity.this, "Inscription réussie", Toast.LENGTH_LONG).show();
				Log.d("Inscription �ffectu� =>",user.getID());
			}

			@Override
			public void failure(StackMobException arg0) {
				Toast.makeText(InscriptionActivity.this, "inscriptin échouée", Toast.LENGTH_LONG).show();
				Log.d("inscriptin échouée",user.getID());
			}
			
		});*/
	}

}
