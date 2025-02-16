package stockagemanagement.model;

public class Produit {
	private int idProduit;
	private String nameProduit;
	private String descriptionProduit;
	private int quantite;
	private float prix;
	private Categorie categorie;
	
	   //declaration d'une enum categorie
    public enum Categorie {
        Électronique, Beauté, Maison, Vêtements, Alimentation
    }
	
	
public Produit(int idProduit, String nameProduit, String descriptionProduit, int quantite, float prix, Categorie categorie) {
		super();
		this.idProduit = idProduit;
		this.nameProduit = nameProduit;
		this.descriptionProduit = descriptionProduit;
		this.quantite = quantite;
		this.prix = prix;
		this.categorie = categorie;
}



public Produit(String nameProduit, String descriptionProduit, int quantite, float prix, Categorie categorie) {
	super();
	this.nameProduit = nameProduit;
	this.descriptionProduit = descriptionProduit;
	this.quantite = quantite;
	this.prix = prix;
	this.categorie = categorie;
}



public int getIdProduit() {
	return idProduit;
}



public void setIdProduit(int idProduit) {
	this.idProduit = idProduit;
}



public String getNameProduit() {
	return nameProduit;
}



public void setNameProduit(String nameProduit) {
	this.nameProduit = nameProduit;
}



public String getDescriptionProduit() {
	return descriptionProduit;
}



public void setDescriptionProduit(String descriptionProduit) {
	this.descriptionProduit = descriptionProduit;
}



public int getQuantite() {
	return quantite;
}



public void setQuantite(int quantite) {
	this.quantite = quantite;
}



public float getPrix() {
	return prix;
}



public void setPrix(float prix) {
	this.prix = prix;
}



public Categorie getCategorie() {
	return categorie;
}



public void setCategorie(Categorie categorie) {
	this.categorie = categorie;
}




}