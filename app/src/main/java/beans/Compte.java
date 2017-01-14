package beans;

import com.stackmob.sdk.model.StackMobModel;

public class Compte extends StackMobModel {

	//private String compte_id;
	private String solde;
	private String titreCompte;
	private String dateDernierMouvement;
	
	public Compte(){
		super(Compte.class);
	}
	
	public Compte(String titre, String solde, String date) {
		super(Compte.class);
		this.solde = solde;
		this.titreCompte = titre;
		this.dateDernierMouvement = date;
	}

	public void setSolde(String solde){
		this.solde = solde;
	}
	
	public void setTitreCompte(String titre){
		this.titreCompte = titre;
	}
	
	public void setDateDernierMouvement(String date){
		this.dateDernierMouvement = date;
	}
	
	public String getSolde(){
		return this.solde;
	}
	
	public String getTitre(){
		return this.titreCompte;
	}
	
	public String getDateDernierMouvemen(){
		return this.dateDernierMouvement;
	}
	
	/*public String getIdCompte(){
		return this.compte_id;
	}*/
}
