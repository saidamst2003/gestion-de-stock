<!-- produit-list.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product Management</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center mb-4">Liste des Produits</h2>
        <div class="mb-3">
            <a href="new" class="btn btn-success">Ajouter Nouveau Produit</a>
        </div>
        <table class="table table-bordered">
            <thead class="thead-dark">
                <tr>
                    <th>ID</th>
                    <th>Nom</th>
                    <th>Description</th>
                    <th>Quantité</th>
                    <th>Prix</th>
                    <th>Catégorie</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="produit" items="${produitList}">
                    <tr>
                        <td>${produit.id}</td>
                        <td>${produit.nomProduit}</td>
                        <td>${produit.descriptionProduit}</td>
                        <td>${produit.quantite}</td>
                        <td>${produit.prix}</td>
                        <td>${produit.categorie}</td>
                        <td>
                            <a href="edit?id=${produit.id}" class="btn btn-warning btn-sm">Modifier</a>
                            <a href="delete?id=${produit.id}" class="btn btn-danger btn-sm" 
                               onclick="return confirm('Êtes-vous sûr de vouloir supprimer ce produit?')">
                                Supprimer
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>