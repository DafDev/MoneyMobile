package com.moneymobile;

import com.stackmob.sdk.api.StackMobOptions;
import com.stackmob.sdk.callback.StackMobModelCallback;
import com.stackmob.sdk.exception.StackMobException;

import beans.ActiveUser;
import beans.UserMap;
import util.ActivityUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import beans.User;

public class ConnexionActivity extends BaseActivity {


	private User user;
	private TextView mUsername;
	private TextView mPassword;
	private Button mLogin;
	private Button mInscription;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_connexion);
		mUsername = (TextView) findViewById(R.id.usernameEditText);
		mPassword = (TextView) findViewById(R.id.passwordEditText);
        mLogin = (Button) findViewById(R.id.login); 
        mInscription = (Button) findViewById(R.id.inscription); 
        Log.d("init bouton login => ",mUsername.getText().toString()+"");
        final Bundle newBundle = new Bundle();
        mInscription.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				Log.d("Envoi vers inscrition","");
				ActivityUtil.switchActivity(ConnexionActivity.this, InscriptionActivity.class, newBundle, true);				
			}
        	
        });
		//Toast.makeText(ConnexionActivity.this, "J'ai clik� sur login", Toast.LENGTH_SHORT).show();
        mLogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				//PARTIE BENAZZOUZ
				/*
				//Log.d("J'ai clike sur login =>",user.getID());
				//Toast.makeText(ConnexionActivity.this, "J'ai clik� sur login", Toast.LENGTH_SHORT).show();
				//ActivityUtil.switchActivity(ConnexionActivity.this, AccueilActivity.class, newBundle, true);
				newBundle.putString("login", mUsername.getText().toString());
				newBundle.putString("password", mPassword.getText().toString());
				//Log.d("init newBundle => ",username.getText().toString()+"");
				user = new User(newBundle.getString("login"),newBundle.getString("password"));
				user.login(new StackMobModelCallback() { //StackMobOptions.depthOf(1),
					  
				    @Override
				    public void success() {
				    	Log.d("Connection succeeded",user.getID());
				    	ActivityUtil.switchActivity(ConnexionActivity.this, AccueilActivity.class, newBundle, true);
				    }
				  
				    @Override
				    public void failure(StackMobException e) {
				    	Log.d("Connection error",user.getID()+" + "+mPassword.getText().toString());
				    }
				});
				*/
				
				//Log.d("avant init user =>",user.getID()+"");
				
				//Log.d("avant init user =>",user.getID()+"");
				//user.save();
				/*user.login(new StackMobModelCallback() {
				    @Override
				    public void success() {
				    	Toast.makeText(ConnexionActivity.this, "Connection succesful", Toast.LENGTH_LONG).show();
				    	Log.d("Connection succesful",user.getID());
				    	ActivityUtil.switchActivity(ConnexionActivity.this, AccueilActivity.class, newBundle, true);
				    }
				  
				    @Override
				    public void failure(StackMobException e) {
				    	Toast.makeText(ConnexionActivity.this, "Erreur lors de la tentative de connexion", Toast.LENGTH_LONG).show();
				    	Log.d("Erreur lors de la tentative de connexion =>",user.getID());
				    	
				    }
				});*/

				//PARTIE TIMOTHEE (temporaire)
				user = new User(newBundle.getString("login"),newBundle.getString("password"));
				String name = mUsername.getText().toString();
				String pass = mPassword.getText().toString();
				HashMap<String, String> usermap = UserMap.getUsers();
				if(usermap.containsKey(name) && usermap.get(name).equals(pass)) {
					Toast.makeText(ConnexionActivity.this, "Connexion réussie", Toast.LENGTH_LONG).show();
					ActiveUser.setUser(user);
					ActivityUtil.switchActivity(ConnexionActivity.this, AccueilActivity.class, newBundle, true);
				}
				else{
					Toast.makeText(ConnexionActivity.this, "Utilisateur non inscrit", Toast.LENGTH_LONG).show();
				}


			}
		});
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.connexion, menu);
		return true;
	}
	
	public void connectUserClick(){
		//Bundle newBundle = new Bundle();
		Log.d("Login clicked",user.getID());
	}

}
