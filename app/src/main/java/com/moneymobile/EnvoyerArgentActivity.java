package com.moneymobile;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import util.ActivityUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import beans.Historique;
import beans.Transaction;
import beans.User;

import com.stackmob.sdk.api.StackMob;
import com.stackmob.sdk.api.StackMobOptions;
import com.stackmob.sdk.api.StackMobQuery;
import com.stackmob.sdk.callback.StackMobQueryCallback;
import com.stackmob.sdk.exception.StackMobException;

public class EnvoyerArgentActivity extends BaseActivity {

	private EditText mMontant;
	private EditText mDestinataire;
	private Button mValider;
	private Button mMenu;
	private Button mDeconnexion;
	private Bundle newBundle;
	private User loggedInUser;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_envoyer_argent);
		
		mMontant 		= (EditText)findViewById(R.id.montantEditText);
		mDestinataire	= (EditText)  findViewById(R.id.usernameEditText);
		mValider		= (Button)  findViewById(R.id.valider);
		mMenu			= (Button)  findViewById(R.id.menu_accueil);
		mDeconnexion	= (Button)  findViewById(R.id.deconnexion); // 
		newBundle = new Bundle();
		
		mValider.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				if(StackMob.getStackMob().isLoggedIn()) {
					Log.d("je suis bien connecte","bien bien");
				    User.getLoggedInUser(User.class,StackMobOptions.depthOf(2), new StackMobQueryCallback<User>() {
				        @Override
				        public void success(List<User> list) {
				        	
				        	Log.d("User connect get",list.get(0).getID());
				        	loggedInUser = list.get(0);
				        	int currentSolde = 0;
				            int ancienSold = Integer.valueOf(loggedInUser.getCompteRelation().getSolde());
			    			int montant = Integer.valueOf(mMontant.getText().toString());
				             currentSolde = ancienSold - montant;
				            
				        	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							Date date = new Date();
							Historique histo = new Historique();
							
							loggedInUser.getCompteRelation().setSolde(currentSolde+"");
							loggedInUser.getListHistorique().add(new Historique(dateFormat.format(date),"Virement",mDestinataire.getText().toString(),currentSolde +""));
							loggedInUser.getListTransaction().add(new Transaction(dateFormat.format(date),"Virement",mDestinataire.getText().toString(),currentSolde +""));
							Log.d("Saving user connect",list.get(0).getID());
							loggedInUser.save(StackMobOptions.depthOf(2));
							
				            User.query(User.class, new StackMobQuery().fieldIsEqualTo("username", mDestinataire.getText().toString()),StackMobOptions.depthOf(2), new StackMobQueryCallback<User>(){
								@Override
								public void failure(StackMobException arg0) {
									// TODO Auto-generated method stub
									
								}

								@Override
								public void success(List<User> arg0) {
									
									DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
									Date date = new Date();
									Log.d("Receiver user found",arg0.get(0).getID());
									User userDestinataire = arg0.get(0);
									int ancienSold = Integer.valueOf(userDestinataire.getCompteRelation().getSolde());
					    			int montant = Integer.valueOf(mMontant.getText().toString());
					    			int currentSolde =  ancienSold + montant;
									
					    			userDestinataire.getCompteRelation().setSolde(currentSolde+"");
									userDestinataire.getListHistorique().add(new Historique(dateFormat.format(date),"Reception",loggedInUser.getID(),currentSolde +""));
									userDestinataire.getListTransaction().add(new Transaction(dateFormat.format(date),"Reception",loggedInUser.getID(),currentSolde +""));
									Log.d("Saving receiver user ",arg0.get(0).getID());
									userDestinataire.save(StackMobOptions.depthOf(2));
									Log.d("Withdrawal succeeded",currentSolde+"");
								}
				            	
				            });
				        }
				  
				        @Override
				        public void failure(StackMobException e) {
				            //User newlyLoggedInUser = doLogin();
				            //continueWithApp(newlyLoggedInUser);zsszz
				        	ActivityUtil.switchActivity(EnvoyerArgentActivity.this, ConnexionActivity.class, newBundle, true);
				        }
				    });
				} else {
				    //User newlyLoggedInUser = doLogin();
				    //continueWithApp(newlyLoggedInUser);
					ActivityUtil.switchActivity(EnvoyerArgentActivity.this, ConnexionActivity.class, newBundle, true);
				}
				
			}
		});
		
		mMenu.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ActivityUtil.switchActivity(EnvoyerArgentActivity.this, AccueilActivity.class, newBundle, true);
				
			}
		});
		
		mDeconnexion.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ActivityUtil.switchActivity(EnvoyerArgentActivity.this, DeconnexionActivity.class, newBundle, true);
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.envoyer_argent, menu);
		return true;
	}

}
