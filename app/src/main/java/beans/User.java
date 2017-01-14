package beans;

//import com.stackmob.sdk.model.StackMobModel;

import java.util.ArrayList;
import java.util.List;

import com.stackmob.sdk.model.StackMobUser;

public class User extends StackMobUser {
	
	private String phone = "0618319489";
	private List<Historique> user_historique_relation = new ArrayList<Historique>();
	private List<Transaction> user_transaction_relation = new ArrayList<Transaction>();
	private Compte user_compte_relation = new Compte();
	
	public User(String name, String pass){
		super(User.class,name,pass);
		setID(name);
	}
	
	public User(){
		super(User.class);
	}
	
	public void setPhone(String tel){
		this.phone = tel;
	}
	
	public void setCompteRelation(Compte compte){
		this.user_compte_relation = compte;
	}
	
	public String getPhone(){
		return this.phone;
	}
	
	public List<Historique> getListHistorique(){
		return this.user_historique_relation;
	}
	
	public List<Transaction> getListTransaction(){
		return this.user_transaction_relation ;
	}
	
	public Compte getCompteRelation(){
		return this.user_compte_relation;
	}
}
