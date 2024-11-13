package metier;

public class Vente {
	private String idVente;
	private String idProduit;
	private String idClient ;
	private String prixProduit;
	private Integer quantiteProduit;
	public Vente(String idVente, String idProduit, String idClient, String prixProduit, Integer quantiteProduit) {
		super();
		this.idVente = idVente;
		this.idProduit = idProduit;
		this.idClient = idClient;
		this.prixProduit = prixProduit;
		this.quantiteProduit = quantiteProduit;
	}
	public Vente(String string, String string2, String string3, Integer string4) {
		super();
		this.idProduit = string;
		this.idClient = string2;
		this.prixProduit = string3;
		this.quantiteProduit = string4;
	}
	public String getPrixProduit() {
		return prixProduit;
	}
	public void setPrixProduit(String prixProduit) {
		this.prixProduit = prixProduit;
	}
	public Integer getQuantiteProduit() {
		return quantiteProduit;
	}
	public void setQuantiteProduit(Integer quantiteProduit) {
		this.quantiteProduit = quantiteProduit;
	}
	public String getIdVente() {
		return idVente;
	}
	public String getIdProduit() {
		return idProduit;
	}
	public String getIdClient() {
		return idClient;
	}
	

}
