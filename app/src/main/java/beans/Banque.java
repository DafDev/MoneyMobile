package beans;

import com.stackmob.sdk.model.StackMobModel;

public class Banque extends StackMobModel {

	private String solde_compte;
	private String titre_compte = "Compte Depot";
	private User banque_user_relation;
	private String user_id;
	
	public Banque() {
		super(Banque.class);
	}

	public Banque(String solde, String id_user) {
		super(Banque.class);
	}
	
	public void setIdUser(String id){
		this.user_id = id;
	}
	
	public void setSoldeCompte(String solde){
		this.solde_compte = solde;
	}
	
	public void setTitreCompte(String titre){
		this.titre_compte = titre;
	}
	
	public void setUserRelation(User id){
		this.banque_user_relation = id;
	}/**/
	
	public String getSoldeCompte(){
		return this.solde_compte;
	}
	
	public String getTitreCompte(){
		return this.titre_compte;
	}
	
	public User getUserRelation(){
		return this.banque_user_relation;
	}
	
	public String getIdUser(){
		return this.user_id;
	}
	
}
