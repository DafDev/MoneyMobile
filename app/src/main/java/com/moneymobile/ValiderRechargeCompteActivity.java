package com.moneymobile;

import java.util.List;

import util.ActivityUtil;

import com.stackmob.sdk.api.StackMob;
import com.stackmob.sdk.api.StackMobOptions;
import com.stackmob.sdk.api.StackMobQuery;
import com.stackmob.sdk.callback.StackMobQueryCallback;
import com.stackmob.sdk.exception.StackMobException;

import beans.Banque;
import beans.User;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ValiderRechargeCompteActivity extends Activity {

	
	private Bundle bundle;
	private Banque banqueDepot;
	private User loggedInUser;
	private Button menu_accueil;
	private Button deconnexion;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_valider_recharge_compte);
		
		bundle 				= getIntent().getExtras(); 
		menu_accueil 		= (Button) findViewById(R.id.back_menu_accueil);
		deconnexion			= (Button) findViewById(R.id.deconnexion);		
		
		//message_accueil.setText("nouveau solde du compte "+bundle.getString("recharge")+" Euro(s)");
		
		if(StackMob.getStackMob().isLoggedIn()) {
			//Log.d("je suis connecte !!","bien bien");
		    User.getLoggedInUser(User.class,StackMobOptions.depthOf(2), new StackMobQueryCallback<User>() {
		        @Override
		        public void success(List<User> list) {
		        	
		        	//Log.d("User connect get !!",list.get(0).getID());
		        	loggedInUser = list.get(0);
		        	//banqueDepot = loggedInUser.get 
		        	//Log.d("Bank get!!",banqueDepot.getIdUser());
		        	
		        	Banque.query(Banque.class, new StackMobQuery().fieldIsEqualTo("banque_user_relation", loggedInUser.getID()),StackMobOptions.depthOf(2),  new StackMobQueryCallback<Banque>(){

		    			@Override
		    			public void failure(StackMobException arg0) {
		    				// TODO Auto-generated method stub
		    				
		    			}

		    			@Override
		    			public void success(List<Banque> arg0) {
		    				banqueDepot = arg0.get(0);
		    				banqueDepot.setUserRelation(loggedInUser);
		    				Log.d("Request on Bank table",banqueDepot.getUserRelation().getID());
		    				
		    				int currentSolde = 0;
				            int ancienSold = Integer.valueOf(banqueDepot.getSoldeCompte());
			    			int montant = Integer.valueOf(bundle.getInt("recharge"));
				            currentSolde = ancienSold - montant;
				        	banqueDepot.setSoldeCompte(currentSolde+"");
				        	 
				        	currentSolde = 0;
				        	String val = banqueDepot.getUserRelation().getCompteRelation().getSolde();
				            ancienSold = Integer.valueOf(val); 
			    			montant = Integer.valueOf(bundle.getInt("recharge"));
				            currentSolde = ancienSold + montant;
				            
				        	banqueDepot.getUserRelation().getCompteRelation().setSolde(currentSolde+"");
				        	banqueDepot.save(StackMobOptions.depthOf(2));
				        	
				        	bundle.putString("currentSolde", currentSolde+"");
				        	ActivityUtil.switchActivity(ValiderRechargeCompteActivity.this, SoldeCompteActivity.class, bundle, true);
		    			}
		        		
		        	});
		        	/*banqueDepot.setUserRelation(loggedInUser);
		        	*/
		        }

				@Override
				public void failure(StackMobException arg0) {
					// TODO Auto-generated method stub
					
				}
		    });
		} else {
		    //User newlyLoggedInUser = doLogin();
		    //continueWithApp(newlyLoggedInUser);
			Log.d("Erreur lors de la tentative de connexion",":( :(");
			//ActivityUtil.switchActivity(RechargerCompteConfirmationActivity.this, ConnexionActivity.class, bundle, true);		
		}
		
		
		menu_accueil.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				ActivityUtil.switchActivity(ValiderRechargeCompteActivity.this, AccueilActivity.class, bundle, true);
				
			}
		});
		
		deconnexion.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				ActivityUtil.switchActivity(ValiderRechargeCompteActivity.this, DeconnexionActivity.class, bundle, true);
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.valider_recharge_compte, menu);
		return true;
	}

}
