package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import metier.Produit;


public class CrudProduit  {
	public static ObservableList<String> getNoms(){
		ObservableList<String> noms=FXCollections.observableArrayList();
		String req="Select nomMarque from marque;";
		PreparedStatement ps;
		ResultSet rs;
		try {
			ps=SingletonConnection.getCon().prepareStatement(req);
			rs=ps.executeQuery();
			while(rs.next()) {
				String nom=rs.getString(1);
				noms.add(nom);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return noms;
	}

	public static ObservableList<Produit> getAll() {
		String rq="select * from produit;";
		PreparedStatement ps;
		ResultSet rs  ;
		Produit p ;
		ObservableList<Produit> listeProduit = FXCollections.observableArrayList();
		try {
			ps=SingletonConnection.getCon().prepareStatement(rq);
			rs= ps.executeQuery();
			while (rs.next()) {
				p=new Produit(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6));
			    listeProduit.add(p);}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listeProduit;
		
		
	}

	public ResultSet search(int id) {
		String rq="select * from produit where idProduit=?;";
		PreparedStatement ps;
		 try {
			ps=SingletonConnection.getCon().prepareStatement(rq);
			ps.setInt(1, id);
			return ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static int insert(Object c) {
		String rq="insert into produit (nomProduit,descriptionProduit,prixProduit,quantiteProduit,marque) values (?,?,?,?,?);";
		PreparedStatement ps;
		try {
			ps= SingletonConnection.getCon().prepareStatement(rq);
			ps.setString(1,((Produit) c).getNomProduit());
			ps.setString(2,((Produit) c).getDescriptionProduit());
			ps.setString(3,((Produit) c).getPrixProduit());
			ps.setLong(4,((Produit) c).getQuantiteProduit());
			ps.setString(5,((Produit) c).getMarque());
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public static int update(Object c,String nom,String des,String prix,int quant,String Marque) {
		String rq="update produit set nomProduit=?,descriptionProduit=?,prixProduit=?,quantiteProduit=?,marque=? where idProduit=?;";
		PreparedStatement ps;
		try {
			ps= SingletonConnection.getCon().prepareStatement(rq);
			ps.setString(1,nom);
			ps.setString(2,des);
			ps.setString(3,prix);
			ps.setInt(4,quant);
			ps.setString(5,Marque);
			ps.setString(6,((Produit) c).getIdProduit());
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public static int delete(Object c) {
		String rq="delete from produit where idProduit=?;";
		PreparedStatement ps;
		try {
			ps=SingletonConnection.getCon().prepareStatement(rq);
			ps.setString(1, ((Produit) c).getIdProduit());
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}
