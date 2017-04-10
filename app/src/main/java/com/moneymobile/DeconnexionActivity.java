package com.moneymobile;

import android.app.ProgressDialog;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
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

public class DeconnexionActivity extends BaseActivity {

	private Button mDeco;
	private ProgressDialog pd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_deconnexion);

		mDeco = (Button) findViewById(R.id.logoutDeconnexionButton);
		mDeco.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Test présence internet
				ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
				NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

				if (networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected()) {
					new deconnexionTask().execute();
				} else {
					Toast.makeText(DeconnexionActivity.this, "You aren't connected to the Internet", Toast.LENGTH_LONG).show();
					ActivityUtil.switchActivity(DeconnexionActivity.this, AccueilActivity.class, new Bundle(), true);
				}
			}
		});

		/*if(StackMob.getStackMob().isLoggedIn()){
			user.logout(new StackMobModelCallback() {
			    @Override
			    public void success() {
			    	ActivityUtil.switchActivity(DeconnexionActivity.this, ConnexionActivity.class, newBundle, true);
			    }
			  
			    @Override
			    public void failure(StackMobException e) {
			        // the call failed
			    }
			});
		}

		if(StackMob.getStackMob().isLoggedIn()) {
		    User.getLoggedInUser(User.class, new StackMobQueryCallback<User>() {
		        @Override
		        public void success(List<User> list) {
		            User loggedInUser = list.get(0);
		            //continueWithApp(loggedInUser);
		            loggedInUser.logout(new StackMobModelCallback() {
					    @Override
					    public void success() {
					    	ActivityUtil.switchActivity(DeconnexionActivity.this, ConnexionActivity.class, newBundle, true);
					    }
					  
					    @Override
					    public void failure(StackMobException e) {
					        // the call failed
					    }
					});
		        }
		  
		        @Override
		        public void failure(StackMobException e) {
		            //User newlyLoggedInUser = doLogin();
		            //continueWithApp(newlyLoggedInUser);
		        	ActivityUtil.switchActivity(DeconnexionActivity.this, ConnexionActivity.class, newBundle, true);
		        }
		    });
		} else {
		    //User newlyLoggedInUser = doLogin();
		    //continueWithApp(newlyLoggedInUser);
			ActivityUtil.switchActivity(DeconnexionActivity.this, ConnexionActivity.class, newBundle, true);
		}*/
	}

	private class deconnexionTask extends AsyncTask<String, String, String> {

		protected void onPreExecute() {
			super.onPreExecute();

			pd = new ProgressDialog(DeconnexionActivity.this);
			pd.setMessage("Please wait");
			pd.setCancelable(false);
			pd.show();
		}

		protected String doInBackground(String... params) {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost("http://10.0.2.2/moneymobile/deconnexion.php");

			// Request parameters and other properties.
			List<NameValuePair> parameters = new ArrayList<NameValuePair>(2);
			parameters.add(new BasicNameValuePair("cellphone", User.getTelephone()+""));
			parameters.add(new BasicNameValuePair("password", User.getMdp()));
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
			return "";
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			if (pd.isShowing()) {
				pd.dismiss();
				User.nouveau(-1,null,null,null,-1,-1,-1,null);
			}
			System.out.println(result);
			Toast.makeText(DeconnexionActivity.this, result, Toast.LENGTH_LONG).show();
			ActivityUtil.switchActivity(DeconnexionActivity.this, ConnexionActivity.class, new Bundle(), true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.deconnexion, menu);
		return true;
	}

}
