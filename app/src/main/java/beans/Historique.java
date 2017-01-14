package beans;

import java.util.ArrayList;
import java.util.List;

import com.stackmob.sdk.model.StackMobModel;

public class Historique extends StackMobModel{
	//Il serait plus simple de faire une liste de transactions
	//private String historique_id;
	private String date;
	private String transaction;
	private String id_user_destination;
	private String montant_transaction;
	//private List<Transaction> transaction_historique_relation = new ArrayList<Transaction>();
	
	public Historique(){
		super(Historique.class);
	}
	
	public Historique(String date, String transaction, String user_destination, String montant){
		super(Historique.class);
		this.date = date;
		this.transaction = transaction;
		this.id_user_destination = user_destination;
		this.montant_transaction = montant;
	}
	/*public void setHistoriqueId(String id){
		this.historique_id = id;
	}*/
	
	public void setDate(String date){
		this.date = date;
	}
	
	
	
	public String getDate(){
		return this.date;
	}
	
	public void setTransactin(String transaction){
		this.transaction = transaction;
	}
	
	public void setIdUserDestination(String id){
		this.id_user_destination = id;
	}
	
	public void setMontantTransaction(String transaction){
		this.montant_transaction = transaction;
	}
	
	public String getTransactin(){
		return this.transaction ;
	}
	
	public String getIdUserDestination(){
		return this.id_user_destination ;
	}
	
	public String getMontantTransaction(){
		return this.montant_transaction ;
	}
	/*public String getIdHistorique(){
		return this.historique_id ;
	}
	
	public List<Transaction> getListTransaction(){
		return this.transaction_historique_relation ;
	}*/
	
	
	
}
