package com.moneymobile;

import android.app.ProgressDialog;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import beans.Historique;
import util.ActivityUtil;

public class HistoriqueActivity extends BaseActivity {

	//private WebView myWebView = null;

	TextView txtJson;
	ProgressDialog pd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_historique);

		txtJson = (TextView) findViewById(R.id.historiqueTextView);

		// Test présence internet
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

		if (networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected()) {
			//boolean wifi = networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
			Toast.makeText(HistoriqueActivity.this, "vous etes connecté à internet", Toast.LENGTH_LONG).show();
			new JsonTask().execute();
		} else {
			Toast.makeText(HistoriqueActivity.this, "vous n'etes pas connecté à internet", Toast.LENGTH_LONG).show();
			ActivityUtil.switchActivity(HistoriqueActivity.this, AccueilActivity.class, new Bundle(), true);
		}
	}

	private class JsonTask extends AsyncTask<String, String, String> {

		protected void onPreExecute() {
			super.onPreExecute();

			pd = new ProgressDialog(HistoriqueActivity.this);
			pd.setMessage("Please wait");
			pd.setCancelable(false);
			pd.show();
		}

		protected String doInBackground(String... params) {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost("http://10.0.2.2/moneymobile/historique.php");

			// Request parameters and other properties.
			List<NameValuePair> parameters = new ArrayList<NameValuePair>(2);
			parameters.add(new BasicNameValuePair("telephone", "012345"));
			parameters.add(new BasicNameValuePair("mdp", "aaa"));
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

					while ((line = reader.readLine()) != null) {
						buffer.append(line + "\n");
					}

					if (buffer.toString().equals("erreur")) {
						return "This user is not registered";
					}

					JSONArray jArray = new JSONArray(buffer.toString());
					StringBuilder result = new StringBuilder();
					JSONArray jArrayElem;
					for (int i=0; i < jArray.length(); i++)
					{
						try {
							jArrayElem = (JSONArray) jArray.get(i);
							result.append("Transaction "+i+"\n");
							result.append("Sender : "+jArrayElem.get(1)+"\n");
							result.append("Receiver : "+jArrayElem.get(2)+"\n");
							result.append("Amount : "+jArrayElem.get(3)+"\n");
							result.append("Change rate : "+jArrayElem.get(4)+"\n");
							result.append("Date and time : "+jArrayElem.get(5)+"\n\n");
						}
						catch (JSONException e) {e.printStackTrace();}
					}
					return result.toString();
				}
			}
			catch (IOException e) {e.printStackTrace();}
			catch (JSONException e) {e.printStackTrace();}
			return "erreurFin";
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			if (pd.isShowing()) {
				pd.dismiss();
			}
			txtJson.setText(result);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.historique, menu);
		return true;
	}

}