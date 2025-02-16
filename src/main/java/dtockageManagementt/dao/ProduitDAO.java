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
    private String jdbcURL = "jdbc:mysql://localhost:3306/stockmanagement?useSSL=false&serverTimezone=UTC";
    private String jdbcUsername = "root";
    private String jdbcPassword = "123456";

    private static final String INSERT_PRODUITS_SQL = "INSERT INTO produits (nomProduit, descriptionProduit, quantite, prix, categorie) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_PRODUIT_BY_ID = "SELECT * FROM produits WHERE id = ?";
    private static final String SELECT_ALL_PRODUITS = "SELECT * FROM produits";
  
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void insertProduit(Produit produit) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUITS_SQL)) {
            preparedStatement.setString(1, produit.getNameProduit());
            preparedStatement.setString(2, produit.getDescriptionProduit());
            preparedStatement.setInt(3, produit.getQuantite());
            preparedStatement.setFloat(4, produit.getPrix());
            preparedStatement.setString(5, produit.getCategorie().name());
            preparedStatement.executeUpdate();
        }
    }

    public List<Produit> selectAllProduits() {
        List<Produit> produits = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUITS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                produits.add(extractProduitFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produits;
    }

    public Produit selectProduit(int idProduit) {
        Produit produit = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUIT_BY_ID)) {
            preparedStatement.setInt(1, idProduit);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                produit = extractProduitFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produit;
    }
    private Produit extractProduitFromResultSet(ResultSet rs) throws SQLException {
        return new Produit(
            rs.getInt("idProduit"),
            rs.getString("nomProduit"),
            rs.getString("descriptionProduit"),
            rs.getInt("quantite"),
            rs.getFloat("prix"),
            Categorie.valueOf(rs.getString("categorie"))
        );
    }
  }


