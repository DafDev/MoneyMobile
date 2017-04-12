package com.moneymobile;

import util.ActivityUtil;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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
import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import beans.User;

public class ConnexionActivity extends BaseActivity {

	private TextView mCellphone;
	private TextView mPassword;
	private Button mConnect;
	private Button mRegister;
	private ProgressDialog pd;
	private String CellphoneString;
	private String PasswordString;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_connexion);

		mCellphone = (TextView) findViewById(R.id.cellphoneConnexionEditText);
		mPassword = (TextView) findViewById(R.id.passwordConnexionEditText);
		mConnect = (Button) findViewById(R.id.validateConnexionButton);
		mRegister = (Button) findViewById(R.id.resgisterConnexionButton);

		mRegister.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ActivityUtil.switchActivity(ConnexionActivity.this, InscriptionActivity.class, new Bundle(), true);
			}
		});
		mConnect.setOnClickListener(new View.OnClickListener() {
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

				// Test présence internet
				ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
				NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

				if (networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected()) {
					//boolean wifi = networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
					CellphoneString = mCellphone.getText().toString();
					PasswordString = mPassword.getText().toString();
					new connexionTask().execute();
				} else {
					Toast.makeText(ConnexionActivity.this, "Vous n'etes pas connecté à Internet", Toast.LENGTH_LONG).show();
					ActivityUtil.switchActivity(ConnexionActivity.this, AccueilActivity.class, new Bundle(), true);
					return;
				}
			}
		});
        
	}

	private class connexionTask extends AsyncTask<String, String, String> {

		protected void onPreExecute() {
			super.onPreExecute();
			pd = new ProgressDialog(ConnexionActivity.this);
			pd.setMessage("Please wait");
			pd.setCancelable(false);
			pd.show();
		}

		protected String doInBackground(String... params) {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost("http://10.0.2.2/moneymobile/connexion.php");
			// Request parameters and other properties.
			List<NameValuePair> parameters = new ArrayList<NameValuePair>(2);
			parameters.add(new BasicNameValuePair("cellphone", CellphoneString));
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

					String result = buffer.toString();

					return result;
				}
			}
			catch (IOException e) {e.printStackTrace();}
			return "connexionTask error";
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			final String json = result.substring(1);
			if (pd.isShowing()) {
				pd.dismiss();
			}
			else {}
			if (result.charAt(0) == 'A') {
				Toast.makeText(ConnexionActivity.this, "Connexion reussie", Toast.LENGTH_LONG).show();

				JSONArray jArray;
				try {
					jArray = new JSONArray(json);
					User.nouveau(jArray.getInt(0), jArray.getString(1), jArray.getString(2), jArray.getString(3), jArray.getInt(4), jArray.getInt(5), jArray.getInt(6), jArray.getString(7));
				}
				catch (JSONException e) {e.printStackTrace();}
				ActivityUtil.switchActivity(ConnexionActivity.this, AccueilActivity.class, new Bundle(), true);
			}
			else if (result.charAt(0) == 'B') {
				AlertDialog alertDialog = new AlertDialog.Builder(ConnexionActivity.this).create();
				alertDialog.setTitle("Alert");
				alertDialog.setMessage("Vous étiez déjà connecté, si vous vous êtes déconnecté la dernière fois votre compte est compromis, changez votre mot de passe");
				alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
								dialog.dismiss();

								JSONArray jArray;
								try {
									jArray = new JSONArray(json);
									User.nouveau(jArray.getInt(0), jArray.getString(1), jArray.getString(2), jArray.getString(3), jArray.getInt(4), jArray.getInt(5), jArray.getInt(6), jArray.getString(7));
								}
								catch (JSONException e) {e.printStackTrace();}
								ActivityUtil.switchActivity(ConnexionActivity.this, AccueilActivity.class, new Bundle(), true);
							}
						});
				alertDialog.show();

			}
			else if (result.charAt(0) == 'C') {
				Toast.makeText(ConnexionActivity.this, "Mauvais telephone ou mot de passe", Toast.LENGTH_LONG).show();
			}
			else if (result.charAt(0) == 'D') {
				Toast.makeText(ConnexionActivity.this, "Les donnees sont incompletes", Toast.LENGTH_LONG).show();
			}/*
			else {
				AlertDialog alertDialog = new AlertDialog.Builder(ConnexionActivity.this).create();
				alertDialog.setTitle("Alert");
				alertDialog.setMessage(result);
				alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					});
				alertDialog.show();
			}*/
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.connexion, menu);
		return true;
	}

}
