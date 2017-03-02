package com.moneymobile;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import beans.Historique;
import util.ActivityUtil;

public class HistoriqueActivity extends BaseActivity {

	private WebView myWebView = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_historique);

		// Test présence internet
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

		if(networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected()) {
			boolean wifi = networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
			Toast.makeText(HistoriqueActivity.this, "vous etes connecté à internet", Toast.LENGTH_LONG).show();

			myWebView = (WebView) findViewById(R.id.historiquewebview);

			myWebView.loadUrl("http://89.92.171.171");
			myWebView.setWebViewClient(new WebViewClient());
		}

		else {
			Toast.makeText(HistoriqueActivity.this, "vous n'etes pas connecté à internet", Toast.LENGTH_LONG).show();
			ActivityUtil.switchActivity(HistoriqueActivity.this, AccueilActivity.class, new Bundle(), true);
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// Check if the key event was the Back button and if there's history
		if ((keyCode == KeyEvent.KEYCODE_BACK) && myWebView.canGoBack()) {
			myWebView.goBack();
			return true;
		}
		// If it wasn't the Back key or there's no web page history, bubble up to the default
		// system behavior (probably exit the activity)
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.historique, menu);
		return true;
	}

}
