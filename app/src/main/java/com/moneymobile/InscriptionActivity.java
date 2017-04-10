package com.moneymobile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import util.ActivityUtil;

import android.app.ProgressDialog;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class InscriptionActivity extends BaseActivity {

	/*private Banque banqueDepot;
	private User user;
	private Bundle newBundle;*/
	private Button mValider;
	private EditText mFirstname;
	private EditText mLastname;
	private EditText mCellphone;
	private EditText mIBAN;
	private EditText mBIC;
	private EditText mPassword;

	private String FirstnameString;
	private String LastnameString;
	private String CellphoneString;
	private String IBANString;
	private String BICString;
	private String PasswordString;
	private ProgressDialog pd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inscription);
		
		mValider = (Button) findViewById(R.id.validateIncriptionButton);
		mFirstname = (EditText) findViewById(R.id.firstnameInscriptionEditText);
		mLastname = (EditText) findViewById(R.id.lastnameInscriptionEditText);
		mCellphone = (EditText) findViewById(R.id.cellphoneInscriptionEditText);
		mIBAN = (EditText) findViewById(R.id.ibanInscriptionEditText);
		mBIC = (EditText) findViewById(R.id.bicInscriptionEditText);
		mPassword = (EditText) findViewById(R.id.passwordInscriptionEditText);

		// Test présence internet
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

		if (networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected()) {
			//boolean wifi = networkInfo.getType() == ConnectivityManager.TYPE_WIFI;

		} else {
			Toast.makeText(InscriptionActivity.this, "Vous n'etes pas connecté à Internet", Toast.LENGTH_LONG).show();
			ActivityUtil.switchActivity(InscriptionActivity.this, AccueilActivity.class, new Bundle(), true);
		}

		/*newBundle = new Bundle();
		banqueDepot = new Banque();*/
		
		mValider.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
			FirstnameString = mFirstname.getText().toString();
			LastnameString = mLastname.getText().toString();
			CellphoneString = mCellphone.getText().toString();
			IBANString = mIBAN.getText().toString();
			BICString = mBIC.getText().toString();
			PasswordString = mPassword.getText().toString();
			new inscriptionTask().execute();
			}
		});
		
	}

	private class inscriptionTask extends AsyncTask<String, String, String> {

		protected void onPreExecute() {
			super.onPreExecute();

			pd = new ProgressDialog(InscriptionActivity.this);
			pd.setMessage("Please wait");
			pd.setCancelable(false);
			pd.show();
		}

		protected String doInBackground(String... params) {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost("http://10.0.2.2/moneymobile/inscription.php");

			// Request parameters and other properties.
			List<NameValuePair> parameters = new ArrayList<NameValuePair>(6);
			parameters.add(new BasicNameValuePair("firstname", FirstnameString));
			parameters.add(new BasicNameValuePair("lastname", LastnameString));
			parameters.add(new BasicNameValuePair("cellphone", CellphoneString));
			parameters.add(new BasicNameValuePair("iban", IBANString));
			parameters.add(new BasicNameValuePair("bic", BICString));
			parameters.add(new BasicNameValuePair("password", PasswordString));
			try {
				httppost.setEntity(new UrlEncodedFormEntity(parameters, "UTF-8"));
			}
			catch (UnsupportedEncodingException e) {e.printStackTrace();}

			//Execute and get the response
			try {
				HttpResponse response = httpclient.execute(httppost);
				HttpEntity entity = response.getEntity();

				if (entity != null) {
					InputStream instream = entity.getContent();
					StringBuilder buffer = new StringBuilder();
					String line;
					BufferedReader reader = new BufferedReader(new InputStreamReader(instream, "UTF-8"));

					line = reader.readLine();
					buffer.append(line);
					while ((line = reader.readLine()) != null) {
						buffer.append("\n" + line);
					}

					return buffer.toString();
				}
			}
			catch (IOException e) {e.printStackTrace();}
			return "inscriptionTask error";
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			if (pd.isShowing()) {
				pd.dismiss();
			}
			Toast.makeText(InscriptionActivity.this, result, Toast.LENGTH_LONG).show();
			ActivityUtil.switchActivity(InscriptionActivity.this, ConnexionActivity.class, new Bundle(), true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.inscription, menu);
		return true;
	}
	
	/*public void inscriptionUser(){
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		
		/*------------- Creation du compte utilisateur ------------
		
		/*Compte compte = new Compte("Compte Mobile","0",dateFormat.format(date));
		user = new User(mUsername.getText().toString(),mPassword.getText().toString());
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
			
		});
		
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
			
		});


	}*/

}
