package dtockageManagementt.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import stockagemanagement.model.Produit;
import stockagemanagement.model.Produit.Categorie;

public class ProduitDAO {
	 private String jdbcURL = "jdbc:mysql://localhost:3306/stockmanagement?useSSL=false";
	 private String jdbcUsername = "root";
	 private String jdbcPassword = "root";
	

	    private static final String INSERT_PRODUITS_SQL = "INSERT INTO produits" + "  (nom, description, quantité,pirx,categorie) VALUES " +
	        " (?, ?, ?, ?, ?);";

	    private static final String SELECT_PRODUIT_BY_ID = "select idProduit,nameProduit,descriptionProduit,quantite,prix,categorie from users where idProduit =?";
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
	    	}catch (Exception e) {
	    		e.printStackTrace();	       
	    	}
	    }
	     
	    //update produit 
	    
	public boolean updateProduit(Produit produit) throws SQLException{
		boolean rowUpdated;
		try (Connection connection =getConnection(); 
			    PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUITS_SQL)) {
		statement.setString(1, produit.getNom());
	    statement.setString(2, produit.getDescription());
    	statement.setInt(3, produit.getQuantité());
	    statement.setFloat(4, produit.getPirx());
	    statement.setString(5, produit.getCategorie().name());		
		
	    rowUpdated = statement.executeUpdate() > 0;
	}
	return rowUpdated;
		
	}
	    //select produit by id
	
	  public Produit selectProduit(int id) {
	        Produit produit = null;
	        // Step 1: Establishing a Connection
	        try (Connection connection = getConnection();
	            // Step 2:Create a statement using connection object
	            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUIT_BY_ID);) {
	            preparedStatement.setInt(1, id);
	            System.out.println(preparedStatement); 
	            // Step 3: Execute the query or update query
	            ResultSet rs = preparedStatement.executeQuery();

	            // Step 4: Process the ResultSet object.
	            while (rs.next()) {
	                
	                String nom = rs.getString("nom");
	                String description = rs.getString("description");
	                int quantité = rs.getInt("quantité");
	                float prix = rs.getFloat("prix");
	                Categorie categorie = Categorie.valueOf(rs.getString("Categorie"));
	                produit = new Produit(id, nom, description, quantité,prix, categorie);

	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return produit;
	    } 
	    //select Produits

	  public List < Produit > selectAllProduits() {
	       List<Produit> produits =new ArrayList<>(); 
	        // Step 1: Establishing a Connection
	        try (Connection connection = getConnection();
	            // Step 2:Create a statement using connection object
	            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUITS );) {
	            System.out.println(preparedStatement); 
	            // Step 3: Execute the query or update query
	            ResultSet rs = preparedStatement.executeQuery();

	            // Step 4: Process the ResultSet object.
	            while (rs.next()) {
	                int id = rs.getInt("id");
	                String nom = rs.getString("nom");
	                String description = rs.getString("description");
	                int quantité = rs.getInt("quantité");
	                float prix = rs.getFloat("prix");
	                Categorie categorie = Categorie.valueOf(rs.getString("Categorie"));
	                produits.add( new Produit(id, nom, description, quantité,prix, categorie));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return produits;
	    } 
}