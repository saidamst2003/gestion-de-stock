package net.javaguides.usermanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.catalina.User;

import net.javaguides.usemanagement.model.Produit;

public class ProduitDAO {
	 private String jdbcURL = "jdbc:mysql://localhost:3306/stockmanagement?useSSL=false";
	 private String jdbcUsername = "root";
	 private String jdbcPassword = "root";
	

	    private static final String INSERT_PRODUITS_SQL = "INSERT INTO produits" + "  (nom, description, quantité,pirx,categorie) VALUES " +
	        " (?, ?, ?, ?, ?);";

	    private static final String SELECT_PRODUITS_BY_ID = "select idProduit,nameProduit,descriptionProduit,quantite,prix,categorie from users where idProduit =?";
	    private static final String SELECT_ALL_PRODUITS = "select * from produits";
	    private static final String DELETE_PRODUITS_SQL = "delete from produits where idProduit = ?;";
	    private static final String UPDATE_PRODUITS_SQL = "update produits set nameProduit = ?,descriptionProduit= ?,quantite= ?, prix =?, categorie =? where idProduit = ?;";

	    public ProduitDAO() {}

	    
	    protected Connection getConnection() {
	        Connection connection = null;
	        try { 
	            Class.forName("com.mysql.jdbc.Driver");
	            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (ClassNotFoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        return connection;
	    }
	    //create or insert  produit 
	    public void insertProduit(Produit produit) throws SQLException{
	    	try(Connection connection = getConnection();
	    			    PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUITS_SQL)) {
	    		    preparedStatement.setString(1,produit.getNom());
	    		    preparedStatement.setString(2,produit.getDescription());
	    		    preparedStatement.setInt(3,produit.getQuantité());
	    		    preparedStatement.setFloat(4,produit.getPirx());
	    		    preparedStatement.setString(5,produit.getCategorie().name());
	    		    preparedStatement.executeUpdate();
	    	}catch (Exeception e) {
	    		e.printStackTrace();	       
	    	}
	    }
	     
	    //update user
	    
	public boolean updateProduit(Produit produit) throws SQLExcsption{
		boolean rowUpdated;
		try (Connection connection =getConnection(); PrepareStatement statement =connection.prepareStatement(UPDATE_PRODUITS_SQL)){
		statement.setString(1, produit.getNom());
	    statement.setString(2, produit.getDescription());
    	statement.setInt(3, produit.getQuantité());
	    statement.setFloat(4, produit.getPirx());
	    statement.setString(5, produit.getCategorie().name());		
		
	    rowUpdated = statement.excuteUpdate() > 0;
	}
	return rowUpdated;
		
	}
	    //select user by id
	    
}    

