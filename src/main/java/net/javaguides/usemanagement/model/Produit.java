package net.javaguides.usemanagement.model;

public class Produit {
	private int id;
	private String nom;
	private String description;
	private int quantité;
	private float pirx;
	private Categorie categorie;
	
	   //declaration d'une enum categorie
    public enum Categorie {
        Électronique, Beauté, Maison, Vêtements, Alimentation

    }
	
	
public Produit(int id, String nom, String description, int quantité, float pirx, Categorie categorie) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.quantité = quantité;
		this.pirx = pirx;
		this.categorie = categorie;
}


public int getId() {
	return id;
}


public void setId(int id) {
	this.id = id;
}


public String getNom() {
	return nom;
}


public void setNom(String nom) {
	this.nom = nom;
}


public String getDescription() {
	return description;
}


public void setDescription(String description) {
	this.description = description;
}


public int getQuantité() {
	return quantité;
}


public void setQuantité(int quantité) {
	this.quantité = quantité;
}


public float getPirx() {
	return pirx;
}


public void setPirx(float pirx) {
	this.pirx = pirx;
}


public Categorie getCategorie() {
	return categorie;
}


public void setCategorie(Categorie categorie) {
	this.categorie = categorie;
}

}