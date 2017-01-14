package util;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import beans.Banque;
import beans.User;

import com.moneymobile.RechargerCompteConfirmationActivity;
import com.moneymobile.SoldeCompteActivity;
import com.stackmob.sdk.api.StackMobOptions;
import com.stackmob.sdk.api.StackMobQuery;
import com.stackmob.sdk.callback.StackMobQueryCallback;
import com.stackmob.sdk.exception.StackMobException;

public class ValiderRechargeCompte {

	private Bundle bundle;
	private Banque banqueDepot;
	private User loggedInUser;
	private Button menu_accueil;
	private Button deconnexion;
	private boolean valider;
	private Activity contextActivity;
	
	public ValiderRechargeCompte(Bundle bundle) {
		this.bundle = bundle;
	}
	
	public void setUserLogged(User logged){
		this.loggedInUser = logged;
	}
	
	public void setContextActivity(Activity activityConfirmationRecharge){
		this.contextActivity = activityConfirmationRecharge;
	}
	
	public void recharger(){
		
		
		
		   
    	Banque.query(Banque.class, new StackMobQuery().fieldIsEqualTo("banque_user_relation", loggedInUser.getID()),StackMobOptions.depthOf(2),  new StackMobQueryCallback<Banque>(){

			@Override
			public void failure(StackMobException arg0) {
				Log.d("Ereur lors de la Recharge !!",":(");				
			}

			@Override
			public void success(List<Banque> arg0) {
				banqueDepot = arg0.get(0);
				banqueDepot.setUserRelation(loggedInUser);
				Log.d("Requete sur la table Banque !!",banqueDepot.getUserRelation().getID());
				
				int currentSolde = 0;
	            int ancienSold = Integer.valueOf(banqueDepot.getSoldeCompte());
    			int montant = Integer.valueOf(bundle.getInt("recharge"));
	            currentSolde = ancienSold - montant;
	        	banqueDepot.setSoldeCompte(currentSolde+""); 
	        	 
	        	currentSolde = 0;
	        	String val = banqueDepot.getUserRelation().getCompteRelation().getSolde();
	            ancienSold = Integer.valueOf(val); 
    			montant = Integer.valueOf(bundle.getInt("recharge"));
	            currentSolde = ancienSold + montant;
	            
	        	banqueDepot.getUserRelation().getCompteRelation().setSolde(currentSolde+"");
	        	banqueDepot.save(StackMobOptions.depthOf(2));
	        	
	        	bundle.putString("currentSolde", currentSolde+"");
	        	Log.d("Recharge success !!",":)");
	        	ActivityUtil.switchActivity(contextActivity, SoldeCompteActivity.class, bundle, true);
			}
    		
    	});
	}

}
