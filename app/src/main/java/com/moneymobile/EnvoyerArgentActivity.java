package com.moneymobile;
//ActivityUtil.switchActivity(EnvoyerArgentActivity.this, AccueilActivity.class, new Bundle(), true);
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import util.ActivityUtil;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import beans.Historique;
import beans.Transaction;
import beans.User;

import com.stackmob.sdk.api.StackMob;
import com.stackmob.sdk.api.StackMobOptions;
import com.stackmob.sdk.api.StackMobQuery;
import com.stackmob.sdk.callback.StackMobQueryCallback;
import com.stackmob.sdk.exception.StackMobException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;

public class EnvoyerArgentActivity extends BaseActivity {

	private EditText mMontant;
	private EditText mDestinataire;
	private Button mValider;
	private ProgressDialog pd;
	private String montantString;
	private String destinataireString;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_envoyer_argent);
		
		mMontant 		= (EditText) findViewById(R.id.montantEnvoyerArgentEditText);
		mDestinataire	= (EditText) findViewById(R.id.telephoneEnvoyerArgentEditText);
		mValider		= (Button)  findViewById(R.id.validerEnvoyerArgentButton);
		
		mValider.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Test présence internet
				ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
				NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

				if (networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected()) {
					//boolean wifi = networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
					montantString = mMontant.getText().toString();
					destinataireString = mDestinataire.getText().toString();
					new envoyerargentTask().execute();
				} else {
					Toast.makeText(EnvoyerArgentActivity.this, "Vous n'etes pas connecté à Internet", Toast.LENGTH_LONG).show();
					ActivityUtil.switchActivity(EnvoyerArgentActivity.this, AccueilActivity.class, new Bundle(), true);
				}
			}
		});
	}

	private class envoyerargentTask extends AsyncTask<String, String, String> {

		protected void onPreExecute() {
			super.onPreExecute();
			pd = new ProgressDialog(EnvoyerArgentActivity.this);
			pd.setMessage("Please wait");
			pd.setCancelable(false);
			pd.show();
		}

		protected String doInBackground(String... params) {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost("http://10.0.2.2/moneymobile/envoyerargent.php");
			// Request parameters and other properties.
			List<NameValuePair> parameters = new ArrayList<NameValuePair>(4);
			parameters.add(new BasicNameValuePair("sender", User.getTelephone()));
			parameters.add(new BasicNameValuePair("password", User.getMdp()));
			parameters.add(new BasicNameValuePair("receiver", destinataireString));
			parameters.add(new BasicNameValuePair("amount", montantString));
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
			return "envoyerargentTask error";
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			if (pd.isShowing()) {
				pd.dismiss();
			}
			Toast.makeText(EnvoyerArgentActivity.this, result, Toast.LENGTH_LONG).show();
			ActivityUtil.switchActivity(EnvoyerArgentActivity.this, AccueilActivity.class, new Bundle(), true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.envoyer_argent, menu);
		return true;
	}

}
