package produitmanagement.web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import stockagemanagement.model.Produit;
import stockagemanagement.model.Produit.Categorie;
import dtockageManagementt.dao.ProduitDAO;  // Fixed typo in package name

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = {
    "/produits",
    "/new",
    "/insert",
    "/edit",
    "/update",
    "/delete"
})
public class ProduitServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProduitDAO produitDAO;
    
    @Override
    public void init() throws ServletException {
        produitDAO = new ProduitDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertProduit(request, response);
                    break;
               
                default:
                    listProduit(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException("Database error occurred", e);
        }
    }

    private void listProduit(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<Produit> listProduit = produitDAO.selectAllProduits();
        request.setAttribute("produitList", listProduit);
        RequestDispatcher dispatcher = request.getRequestDispatcher("produit-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("categories", Categorie.values());
        RequestDispatcher dispatcher = request.getRequestDispatcher("produit-form.jsp");
        dispatcher.forward(request, response);
    }

    private void insertProduit(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String nom = request.getParameter("nomProduit");
        String description = request.getParameter("descriptionProduit");
        int quantite = Integer.parseInt(request.getParameter("quantite"));
        float prix = Float.parseFloat(request.getParameter("prix"));
        Categorie categorie = Categorie.valueOf(request.getParameter("categorie"));
        
        Produit newProduit = new Produit(nom, description, quantite, prix, categorie);
        produitDAO.insertProduit(newProduit);
        response.sendRedirect("produits");
    }


}