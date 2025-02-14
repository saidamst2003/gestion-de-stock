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

 

  

}
