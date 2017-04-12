package com.moneymobile;

import android.app.Activity;
import android.app.ProgressDialog;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import beans.User;
import util.ActivityUtil;
import util.ValiderRechargeCompte;


public class RetirerConfirmationActivity extends Activity {

	private TextView message_accueil;
	private Button menu_accueil;
	private Button deconnexion;
	private Bundle bundle;
	private Button valider;
	private Button gererCompte;
	private ValiderRechargeCompte recharge;
	private ProgressDialog pd;
	private int montant;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recharger_compte_confirmation); 
		
		bundle 				= getIntent().getExtras();
		message_accueil		= (TextView) findViewById(R.id.message_accueilRechargercompteconfirmationTextView);
		valider				= (Button) findViewById(R.id.valider);
		gererCompte 		= (Button) findViewById(R.id.gerer_compte);
		menu_accueil 		= (Button) findViewById(R.id.back_menu_accueil);
		deconnexion			= (Button) findViewById(R.id.deconnexion);

		montant				= bundle.getInt("retirer");
		
		
		message_accueil.setText("Montant du retrait "+montant+" Euro(s)");
		recharge = new ValiderRechargeCompte(bundle);
		
		
		
		menu_accueil.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				ActivityUtil.switchActivity(RetirerConfirmationActivity.this, AccueilActivity.class, bundle, true);
				
			}
		});
		
		deconnexion.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				ActivityUtil.switchActivity(RetirerConfirmationActivity.this, DeconnexionActivity.class, bundle, true);
				
			}
		});

		valider.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Test présence internet
				ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
				NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

				if (networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected()) {
					new validerTask().execute();
				} else {
					Toast.makeText(RetirerConfirmationActivity.this, "Vous n'etes pas connecté à Internet", Toast.LENGTH_LONG).show();
					ActivityUtil.switchActivity(RetirerConfirmationActivity.this, GererMonCompteActivity.class, new Bundle(), true);
				}

			}
		});
		
		gererCompte.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ActivityUtil.switchActivity(RetirerConfirmationActivity.this, GererMonCompteActivity.class, bundle, true);
			}
		});
		
	}

	private class validerTask extends AsyncTask<String, String, String> {

		protected void onPreExecute() {
			super.onPreExecute();

			pd = new ProgressDialog(RetirerConfirmationActivity.this);
			pd.setMessage("Veuillez patienter");
			pd.setCancelable(false);
			pd.show();
		}

		protected String doInBackground(String... params) {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost("http://10.0.2.2/moneymobile/retrait.php");

			// Request parameters and other properties.
			List<NameValuePair> parameters = new ArrayList<NameValuePair>(2);
			parameters.add(new BasicNameValuePair("telephone", User.getTelephone()));
			parameters.add(new BasicNameValuePair("password", User.getMdp()));
			parameters.add(new BasicNameValuePair("amount", montant+""));
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
					User.setSolde(User.getSolde()-montant);
					return buffer.toString();
				}
			}
			catch (IOException e) {e.printStackTrace();}
			return "erreurFin";
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			if (pd.isShowing()) {
				pd.dismiss();
			}
			Toast.makeText(RetirerConfirmationActivity.this, result, Toast.LENGTH_LONG).show();
			ActivityUtil.switchActivity(RetirerConfirmationActivity.this, GererMonCompteActivity.class, bundle, true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.recharger_compte_confirmation, menu);
		return true;
	}

}
