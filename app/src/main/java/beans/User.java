package beans;

public class User {

	private static User User = new User();

	public static User getInstance() { return User; }

	private User() {}

	public static int getId() {
		return id;
	}
	public static void setId(int id) {
		beans.User.id = id;
	}
	public static String getNom() {
		return nom;
	}
	public static void setNom(String nom) {
		beans.User.nom = nom;
	}
	public static String getPrenom() {
		return prenom;
	}
	public static void setPrenom(String prenom) {
		beans.User.prenom = prenom;
	}
	public static String getTelephone() {
		return telephone;
	}
	public static void setTelephone(String telephone) {
		beans.User.telephone = telephone;
	}
	public static int getSolde() {
		return solde;
	}
	public static void setSolde(int solde) {
		beans.User.solde = solde;
	}
	public static int getIban() {
		return iban;
	}
	public static void setIban(int iban) {
		beans.User.iban = iban;
	}
	public static int getBic() {
		return bic;
	}
	public static void setBic(int bic) {
		beans.User.bic = bic;
	}
	public static String getMdp() {
		return mdp;
	}
	public static void setMdp(String mdp) {
		beans.User.mdp = mdp;
	}

	private static int id;
	private static String nom;
	private static String prenom;
	private static String telephone;
	private static int solde;
	private static int iban;
	private static int bic;
	private static String mdp;

	public static void nouveau(int id2, String nom2, String prenom2, String tel, int solde2, int iban2, int bic2, String mdp2){
		id = id2;
		nom = nom2;
		prenom = prenom2;
		telephone = tel;
		solde = solde2;
		iban = iban2;
		bic = bic2;
		mdp = mdp2;
	}
}