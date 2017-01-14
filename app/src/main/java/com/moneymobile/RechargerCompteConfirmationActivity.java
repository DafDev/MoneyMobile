package com.moneymobile;

import java.util.List;

import util.ActivityUtil;
import util.ValiderRechargeCompte;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import beans.User;

import com.stackmob.sdk.api.StackMob;
import com.stackmob.sdk.api.StackMobOptions;
import com.stackmob.sdk.callback.StackMobQueryCallback;
import com.stackmob.sdk.exception.StackMobException;


public class RechargerCompteConfirmationActivity extends Activity {

	private TextView message_accueil;
	private Button menu_accueil;
	private Button deconnexion;
	private Bundle bundle;
	private Button valider;
	private Button gererCompte;
	private ValiderRechargeCompte recharge;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recharger_compte_confirmation); 
		
		bundle = getIntent().getExtras(); 
		message_accueil		= (TextView) findViewById(R.id.message_accueil);
		valider				= (Button) findViewById(R.id.valider);
		gererCompte 	=  (Button) findViewById(R.id.gerer_compte);
		menu_accueil 		= (Button) findViewById(R.id.back_menu_accueil);
		deconnexion			= (Button) findViewById(R.id.deconnexion); // 
		
		
		message_accueil.setText("Montant de la recharge "+bundle.getInt("recharge")+" Euro(s)");
		recharge = new ValiderRechargeCompte(bundle);
		
		
		
		menu_accueil.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				ActivityUtil.switchActivity(RechargerCompteConfirmationActivity.this, AccueilActivity.class, bundle, true);
				
			}
		});
		
		deconnexion.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				ActivityUtil.switchActivity(RechargerCompteConfirmationActivity.this, DeconnexionActivity.class, bundle, true);
				
			}
		});
		
		valider.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//ActivityUtil.switchActivity(RechargerCompteConfirmationActivity.this, ValiderRechargeCompteActivity.class, bundle, true);
				if(StackMob.getStackMob().isLoggedIn()) {
						User.getLoggedInUser(User.class,StackMobOptions.depthOf(2), new StackMobQueryCallback<User>() {
						@Override
						public void failure(StackMobException arg0) {
							// TODO Auto-generated method stub
						}
	
						@Override
						public void success(List<User> arg0) {
							User userLogged = arg0.get(0);
							recharge.setUserLogged(userLogged);
							recharge.setContextActivity(RechargerCompteConfirmationActivity.this);
							recharge.recharger();
							//if(recharge.recharger()){
								//bundle.putString("currentSolde", userLogged.getCompteRelation().getSolde());
								//Log.d("Recharge success !!",":)");
								
							/*}else{
								Log.d("Ereur lors de la Recharge !!",":(");
							}*/
							
						}	
										
					});
						
				}
			}
		});
		
		gererCompte.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ActivityUtil.switchActivity(RechargerCompteConfirmationActivity.this, GererMonCompteActivity.class, bundle, true);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.recharger_compte_confirmation, menu);
		return true;
	}

}
