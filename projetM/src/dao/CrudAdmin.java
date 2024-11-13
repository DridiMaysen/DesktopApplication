package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import metier.Admin;

public class CrudAdmin {
	public static  int insert(Object a) {
		// inserer un admin 
		String rq="insert into admin (nomAdmin,prenomAdmin,mdpAdmin) values (?,?,?);";
		PreparedStatement ps;
		int rs = 0;
		try {
			ps=SingletonConnection.getCon().prepareStatement(rq);
			ps.setString(1,((Admin) a).getNomAdmin());
			ps.setString(2, ((Admin) a).getPrenomAdmin());
			ps.setString(3,((Admin) a).getMdpAdmin());
			rs=ps.executeUpdate();
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public static int update(Object a) {
		// modifier un admin
		String rq="update admin set nomAdmin=?,prenomAdmin=?,mdpAdmin=? where idAdmin=?;";
		PreparedStatement ps;
		int rs = 0 ;

		try {
			ps= SingletonConnection.getCon().prepareStatement(rq);
			ps.setString(1,((Admin) a).getNomAdmin());
			ps.setString(2, ((Admin) a).getPrenomAdmin());
			ps.setString(3,((Admin) a).getMdpAdmin());
			ps.setString(4,((Admin) a).getIdAdmin());
			rs= ps.executeUpdate();
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public static int delete(Object a) {
		// supprimer un client 
		String rq="delete from admin where idAdmin=?;";
		PreparedStatement ps;
		try {
			ps=SingletonConnection.getCon().prepareStatement(rq);
			ps.setString(1, ((Admin) a).getIdAdmin());
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public static ObservableList<Admin> getAll() {
		String rq="select *from admin;";
		PreparedStatement ps;
		ResultSet rs = null ;
		Admin a = null;
		ObservableList<Admin> listeAdmin = FXCollections.observableArrayList();
		try {
			ps=SingletonConnection.getCon().prepareStatement(rq);
			rs= ps.executeQuery();
			while (rs.next())
				a=new Admin(rs.getString(1),rs.getString(2),rs.getString(3));
			    listeAdmin.add(a);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listeAdmin;
		
		
	}


	public ResultSet search(int id) {
		// Recherche d'un admin par son id 
		String rq="select * from admin where idAdmin =?;";
		PreparedStatement ps ;
		try {
			ps= SingletonConnection.getCon().prepareStatement(rq);
			ps.setInt(1, id);
			return ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	

}
