package metier;

public class Admin {
	private String idAdmin;
	private String nomAdmin;
	private String prenomAdmin;
	private String mdpAdmin;
	public Admin(String idAdmin, String nomAdmin, String prenomAdmin, String mdpAdmin) {
		super();
		this.idAdmin = idAdmin;
		this.nomAdmin = nomAdmin;
		this.prenomAdmin = prenomAdmin;
		this.mdpAdmin = mdpAdmin;
	}
	public Admin(String nomAdmin, String prenomAdmin, String mdpAdmin) {
		super();
		this.nomAdmin = nomAdmin;
		this.prenomAdmin = prenomAdmin;
		this.mdpAdmin = mdpAdmin;
	}
	public String getNomAdmin() {
		return nomAdmin;
	}
	public void setNomAdmin(String nomAdmin) {
		this.nomAdmin = nomAdmin;
	}
	public String getPrenomAdmin() {
		return prenomAdmin;
	}
	public void setPrenomAdmin(String prenomAdmin) {
		this.prenomAdmin = prenomAdmin;
	}
	public String getMdpAdmin() {
		return mdpAdmin;
	}
	public void setMdpAdmin(String mdpAdmin) {
		this.mdpAdmin = mdpAdmin;
	}
	public String getIdAdmin() {
		return idAdmin;
	}
	

}
