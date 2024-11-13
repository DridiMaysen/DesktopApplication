package metier;



public class Produit {
	private String idProduit;
	private String nomProduit ;
	private String descriptionProduit ;
	private String prixProduit ;
	private Integer quantiteProduit ;
	private String Marque;
	public Produit(String idProduit, String nomProduit, String descriptionProduit, String prixProduit, Integer quantiteProduit,String Marque) {
		super();
		this.idProduit = idProduit;
		this.nomProduit = nomProduit;
		this.descriptionProduit = descriptionProduit;
		this.prixProduit = prixProduit;
		this.quantiteProduit = quantiteProduit;
		this.Marque=Marque;
	}
	public Produit(String nomProduit, String descriptionProduit, String prixProduit, Integer quantiteProduit,String Marque) {
		super();
		this.nomProduit = nomProduit;
		this.descriptionProduit = descriptionProduit;
		this.prixProduit = prixProduit;
		this.quantiteProduit = quantiteProduit;
		this.Marque=Marque;
	}
	public String getNomProduit() {
		return nomProduit;
	}
	public void setNomProduit(String nomProduit) {
		this.nomProduit = nomProduit;
	}
	
	public void setDescriptionProduit(String descriptionProduit) {
		this.descriptionProduit = descriptionProduit;
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
	public String getIdProduit() {
		return idProduit;
	}
	public String getMarque() {
		return Marque;
	}
	
	public void setMarque(String Marque) {
		this.Marque=Marque;
	}
	public String getDescriptionProduit() {
		return this.descriptionProduit;
	}

}
