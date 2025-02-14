package produitmanagement.web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import stockagemanagement.model.Produit;
import stockagemanagement.model.Produit.Categorie;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import dtockageManagementt.dao.ProduitDAO;

/**
 * Servlet implementation class ProduitServlet
 */
@WebServlet("/")
public class ProduitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProduitDAO produitDAO;
	
	
	   public void init() {
	        produitDAO = new ProduitDAO();
	    }
       
  

    /** 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 doGet(request, response);
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String action =request.getServletPath();
			//getServeletPath
			switch (action) {
		    case "/new":
                showNewForm(request, response);
                break;
            case "/insert":
                try {
					insertProduit(request, response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                break;
            case "/delete":
                try {
					deleteProduit(request, response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                break;
            case "/edit":
                try {
					showEditForm(request, response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                break;
            case "/update":
                try {
					updateProduit(request, response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                break;
            default:
                try {
					listProduit(request, response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                break;
			}
	}

    private void listProduit(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        List < Produit > listProduit = produitDAO.selectAllProduits();
        request.setAttribute("listProduit", listProduit);
        RequestDispatcher dispatcher = request.getRequestDispatcher("produit-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("produit-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("idProduit"));
        Produit existingProduit = produitDAO.selectProduit(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("produit-form.jsp");
        request.setAttribute("produit", existingProduit);
        dispatcher.forward(request, response);

    }

    private void insertProduit(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        String nom = request.getParameter("nameProduit");
        String description = request.getParameter("descriptionProduit");
        int quantité = Integer.parseInt( request.getParameter("quantite"));
        float prix = Float.parseFloat( request.getParameter("prix"));
        Categorie categorie = Categorie.valueOf(request.getParameter("categorie"));
        Produit newProduit = new Produit(nom, description, quantité, prix, categorie);
        produitDAO.insertProduit(newProduit);
        response.sendRedirect("list");
    }

    private void updateProduit(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("idProduit"));
        String nom = request.getParameter("nameProduit");
        String description = request.getParameter("descriptionProduit");
        int quantité = Integer.parseInt( request.getParameter("quantité"));
        float prix = Float.parseFloat( request.getParameter("prix"));
        Categorie categorie = Categorie.valueOf(request.getParameter("categorie"));

        Produit book = new Produit(id,nom, description, quantité, prix,categorie);
        produitDAO.updateProduit(book);
        response.sendRedirect("list");
    }

    private void deleteProduit(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("idProduit"));
        produitDAO.deleteProduit(id);
        response.sendRedirect("list");

    }

}
