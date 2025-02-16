
<!-- produit-form.jsp -->
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
        <div class="card">
            <div class="card-header">
                <h2>${produit != null ? "Modifier Produit" : "Ajouter Nouveau Produit"}</h2>
            </div>
            <div class="card-body">
                <form action="${produit != null ? 'update' : 'insert'}" method="post">
                    <input type="hidden" name="id" value="${produit.id}" />
                    
                    <div class="form-group">
                        <label>Nom:</label>
                        <input type="text" name="nomProduit" class="form-control" 
                               value="${produit.nomProduit}" required>
                    </div>

                    <div class="form-group">
                        <label>Description:</label>
                        <textarea name="descriptionProduit" class="form-control" 
                                  required>${produit.descriptionProduit}</textarea>
                    </div>

                    <div class="form-group">
                        <label>Quantité:</label>
                        <input type="number" name="quantite" class="form-control" 
                               value="${produit.quantite}" required>
                    </div>

                    <div class="form-group">
                        <label>Prix:</label>
                        <input type="number" step="0.01" name="prix" class="form-control" 
                               value="${produit.prix}" required>
                    </div>

                    <div class="form-group">
                        <label>Catégorie:</label>
                        <select name="categorie" class="form-control" required>
                            <c:forEach var="categorie" items="${categories}">
                                <option value="${categorie}" 
                                    ${produit.categorie == categorie ? 'selected' : ''}>
                                    ${categorie}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <button type="submit" class="btn btn-primary">Enregistrer</button>
                    <a href="produits" class="btn btn-secondary">Annuler</a>
                </form>
            </div>
        </div>
    </div>