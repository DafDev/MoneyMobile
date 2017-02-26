package com.moneymobile;

import java.util.List;

import beans.ActiveUser;
import util.ActivityUtil;
import android.os.Bundle;
import android.view.Menu;
import beans.User;

import com.stackmob.sdk.api.StackMob;
import com.stackmob.sdk.callback.StackMobModelCallback;
import com.stackmob.sdk.callback.StackMobQueryCallback;
import com.stackmob.sdk.exception.StackMobException;

public class DeconnexionActivity extends BaseActivity {

	//private User mUser;
	Bundle newBundle = new Bundle();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_deconnexion);
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
		}*/
		
		if(StackMob.getStackMob().isLoggedIn()) {
		    User.getLoggedInUser(User.class, new StackMobQueryCallback<User>() {
		        @Override
		        public void success(List<User> list) {
		            User loggedInUser = list.get(0);
		            //continueWithApp(loggedInUser);
		            loggedInUser.logout(new StackMobModelCallback() {
					    @Override
					    public void success() {
							ActiveUser.setUser(null);
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
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.deconnexion, menu);
		return true;
	}

}
