package beans;

import java.util.ArrayList;
import java.util.List;

import com.stackmob.sdk.model.StackMobModel;

public class Transaction  extends StackMobModel{

	private String date;
	private String transaction;
	private String id_user_destination;
	private String montant_transaction;
	private boolean done = false;
	
	public Transaction(String date,String transaction,String id_destination, String montant) {
		super(Transaction.class);
		this.transaction = transaction;
		this.id_user_destination = id_destination;
		this.montant_transaction = montant;
		this.date = date;
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
	
	/*public String getIdTransaction(){
		return this.transaction_id ;
	}*/
	
	/*public List<Compte> getListComptes(){
		return this.transaction_compte_relation ;
	}*/

}
