package metier;

public class Marque {
	private String idMarque;
	private String nomMarque;
	public Marque(String idMarque, String nomMarque) {
		super();
		this.idMarque = idMarque;
		this.nomMarque = nomMarque;
	}
	public Marque(String nomMarque) {
		super();
		this.nomMarque = nomMarque;
	}
	public String getNomMarque() {
		return nomMarque;
	}
	public void setNomMarque(String nomMarque) {
		this.nomMarque = nomMarque;
	}
	public String getIdMarque() {
		return idMarque;
	}
	

}
