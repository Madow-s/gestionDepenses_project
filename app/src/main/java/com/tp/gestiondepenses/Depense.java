package com.tp.gestiondepenses;

public class Depense {
    private int id;
    private String titre;
    private double montant;
    private String categorie;
    private String date;

    public Depense(int id, String titre, double montant, String categorie, String date) {
        this.id = id;
        this.titre = titre;
        this.montant = montant;
        this.categorie = categorie;
        this.date = date;
    }

    public Depense(String titre, double montant, String categorie, String date) {
        this.titre = titre;
        this.montant = montant;
        this.categorie = categorie;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
