package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import metier.Client;

public class CrudClient  {
	//afficher la liste des clients
	
	public static  int insert(Object c) {
		// inserer un client 
		String rq="insert into client (nomClient,prenomClient,mdpClient,teleClient,addClient,genre) values (?,?,?,?,?,?);";
		PreparedStatement ps;
		int rs = 0;
		try {
			ps=SingletonConnection.getCon().prepareStatement(rq);
			ps.setString(1,((Client) c).getNomClient());
			ps.setString(2, ((Client) c).getPrenomClient());
			ps.setString(3,((Client) c).getMdpClient());
			ps.setString(4,((Client) c).getTeleClient());
			ps.setString(5,((Client) c).getAddClient());
			ps.setString(6,((Client) c).getGenre());
			rs=ps.executeUpdate();
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public static int update(Object c,String nom,String prenom,String mdp,String tele,String add,String genre) {
		// modifier un client
		String rq="update client set nomClient=?,prenomClient=?,mdpClient=?,teleClient=?,addClient=?,genre=? where idClient=?;";
		PreparedStatement ps;
		int rs = 0 ;

		try {
			ps= SingletonConnection.getCon().prepareStatement(rq);
			ps.setString(1,nom);
			ps.setString(2, prenom);
			ps.setString(3,mdp);
			ps.setString(4,tele);
			ps.setString(5,add);
			ps.setString(6,genre);
			ps.setString(7,((Client) c).getIdClient());
			rs= ps.executeUpdate();
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public static int delete(Object c) {
		// supprimer un client 
		String rq="delete from client where idClient=?;";
		PreparedStatement ps;
		try {
			ps=SingletonConnection.getCon().prepareStatement(rq);
			ps.setString(1, ((Client) c).getIdClient());
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public static ObservableList<Client> getAll() {
		String rq="select * from client;";
		PreparedStatement ps;
		ResultSet rs  ;
		Client c;
		ObservableList<Client> listeClient = FXCollections.observableArrayList();
		try {
			ps=SingletonConnection.getCon().prepareStatement(rq);
			rs= ps.executeQuery();
			while (rs.next()) {
		     	c =new Client(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
			    listeClient.add(c);}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listeClient;
		
		
	}


	public ResultSet search(int id) {
		// Recherche d'un client par son id 
		String rq="select * from client where idClient =?;";
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
