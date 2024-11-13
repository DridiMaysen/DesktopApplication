package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import metier.Marque;

public class CrudMarque {
	public static  int insert(Object m) {
		// inserer une marque
		String rq="insert into marque (nomMarque) values (?);";
		PreparedStatement ps;
		int rs = 0;
		try {
			ps=SingletonConnection.getCon().prepareStatement(rq);
			ps.setString(1,((Marque) m).getNomMarque());
			rs=ps.executeUpdate();
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public static int update(Object m,String nom) {
		// modifier une marque
		String rq="update marque set nomMarque=? where idMarque=?;";
		PreparedStatement ps;
		int rs = 0 ;

		try {
			ps= SingletonConnection.getCon().prepareStatement(rq);
			ps.setString(1,nom);
			ps.setString(2,((Marque) m).getIdMarque());
			rs= ps.executeUpdate();
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public static int delete(Object m) {
		// supprimer un client 
		String rq="delete from marque where idMarque=?;";
		PreparedStatement ps;
		try {
			ps=SingletonConnection.getCon().prepareStatement(rq);
			ps.setString(1, ((Marque) m).getIdMarque());
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public static ObservableList<Marque> getAll() {
		String rq="select *from marque;";
		PreparedStatement ps;
		ResultSet rs  ;
		Marque m ;
		ObservableList<Marque> listeMarque = FXCollections.observableArrayList();
		try {
			ps=SingletonConnection.getCon().prepareStatement(rq);
			rs= ps.executeQuery();
			while (rs.next()) {
				m=new Marque(rs.getString(1),rs.getString(2));
			    listeMarque.add(m);}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listeMarque;
	}


	public ResultSet search(String id) {
		// Recherche d'une marque par son id 
		String rq="select * from marque where idMarque =?;";
		PreparedStatement ps ;
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
	
	

}
