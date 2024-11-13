package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import metier.Vente;

public class CrudVente {

	public static ObservableList<Vente> getAll() {
		String rq="select * from vente;";
		PreparedStatement ps;
		ResultSet rs  ;
		Vente v ;
		ObservableList<Vente> listeVente = FXCollections.observableArrayList();
		try {
			ps=SingletonConnection.getCon().prepareStatement(rq);
			rs= ps.executeQuery();
			while (rs.next()) {
				v=new Vente(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5));
			    listeVente.add(v);}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listeVente;
		
		
	}
	//affichage de toute les ventes d' un client
	public ResultSet allByClient(String id) {
		String rq="select * from vente, produit where idClient=? and produit.idProduit=vente.idProduit;";
		PreparedStatement ps;
		try {
			ps= SingletonConnection.getCon().prepareStatement(rq);
			ps.setString(1, id);
			return ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ResultSet search(String id) {
		String rq="select * from vente, produit where idVente=?;";
		PreparedStatement ps;
		try {
			ps= SingletonConnection.getCon().prepareStatement(rq);
			ps.setString(1, id);
			return ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	public static int insert(Object v) {
		String rq="insert into vente (idProduit,idClient,prixProduit,quantiteProduit)values(?,?,?,?);";
		PreparedStatement ps;
		try {
			ps= SingletonConnection.getCon().prepareStatement(rq);
			ps.setString(1, (((Vente) v)).getIdProduit());
			ps.setString(2, (((Vente) v)).getIdClient());
			ps.setString(3, ((Vente) v).getPrixProduit());
			ps.setLong(4, ((Vente) v).getQuantiteProduit());
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public static int update(Object v,String idc,String idp,String prix,Integer quant) {
		String rq="update vente set idProduit=?,idClient=?,prixProduit=?,quantiteProduit=? where idVente=?;";
		PreparedStatement ps;
		try {
			ps= SingletonConnection.getCon().prepareStatement(rq);
			ps.setString(1, idp);
			ps.setString(2, idc);
			ps.setString(3, prix);
			ps.setLong(4, quant);
			ps.setString(5, ((Vente) v).getIdVente());
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

	public static int delete(Object v) {
		String rq="delete from vente where idVente=?;";
		PreparedStatement ps;
		try {
			ps= SingletonConnection.getCon().prepareStatement(rq);
			ps.setString(1, (((Vente) v)).getIdVente());
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}

