package gestion_de_stock;

import java.sql.*;
import javax.swing.*;
public class connexion_data {

	public static void main(String[] args) {
		Connection cnx=connecterDB();

	}
	public static Connection connecterDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("driver ok");
			String url="jdbc:mysql://localhost:3306/gestion_des_produits";
			String user="root";
			String password="";
			Connection cnx=DriverManager.getConnection(url,user,password);
			System.out.println("Connexion bien établie");
			
			return cnx;
			
	}catch(Exception e){
		JOptionPane.showMessageDialog(null, "connexion à la base de données impossible","ERREUR",JOptionPane.ERROR_MESSAGE);
		JOptionPane.showMessageDialog(null, "veuillez activer le port de la base de données","Information",JOptionPane.INFORMATION_MESSAGE);
		
		return null;
	}
}
	
	public static Connection closeconnexion() {
		try {
			String url="jdbc:mysql://localhost:3306/gestion_des_produits";
			String user="root";
			String password="";
			Connection cnx=DriverManager.getConnection(url,user,password);
			cnx.close();
			System.out.println("connexion close");
			return cnx;
		}catch(Exception e){
			e.printStackTrace();
			return null;
			
		}
	}
	}


