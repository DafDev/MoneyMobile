package com.moneymobile;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import beans.ActiveUser;
import beans.User;
import beans.UserMap;

public class GererMonPseudoActivity extends BaseActivity {

	private EditText mNewusername;
	private EditText mNewpassword;
	private Button mUpdate;
	private String newusername;
	private String newpassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gerer_mon_pseudo);
		mNewusername = (EditText) findViewById(R.id.newusernameEditText);
		mNewpassword = (EditText) findViewById(R.id.newpasswordEditText);
		mUpdate = (Button) findViewById(R.id.updateButton);
		newusername = mNewusername.getText().toString();
		newpassword = mNewpassword.getText().toString();

		mUpdate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				HashMap<String, String> userMap = UserMap.getUsers();
				if (userMap.containsKey(newusername)) {
					Toast.makeText(GererMonPseudoActivity.this, "Ce nom est déjà utilisé, tentez un autre", Toast.LENGTH_LONG).show();
				}
				else {
					userMap.remove(ActiveUser.getUser().getID());
					userMap.put(newusername, newpassword);
					ActiveUser.setUser(new User(newusername, newpassword));
					Toast.makeText(GererMonPseudoActivity.this, "Informations remplacées", Toast.LENGTH_LONG).show();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gerer_mon_pseudo, menu);
		return true;
	}

}
