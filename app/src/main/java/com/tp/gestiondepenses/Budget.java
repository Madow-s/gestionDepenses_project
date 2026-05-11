package com.tp.gestiondepenses;

public class Budget {
    private int id;
    private String categorie;
    private double plafond;
    private double depenseActuelle;

    public Budget(String categorie, double plafond, double depenseActuelle) {
        this.categorie = categorie;
        this.plafond = plafond;
        this.depenseActuelle = depenseActuelle;
    }

    public Budget(int id, String categorie, double plafond, double depenseActuelle) {
        this.id = id;
        this.categorie = categorie;
        this.plafond = plafond;
        this.depenseActuelle = depenseActuelle;
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getCategorie() { return categorie; }
    public void setCategorie(String categorie) { this.categorie = categorie; }
    public double getPlafond() { return plafond; }
    public void setPlafond(double plafond) { this.plafond = plafond; }
    public double getDepenseActuelle() { return depenseActuelle; }
    public void setDepenseActuelle(double depenseActuelle) { this.depenseActuelle = depenseActuelle; }

    public double getPourcentage() {
        if (plafond <= 0) return 0;
        return (depenseActuelle / plafond) * 100;
    }

    public double getRestant() {
        return plafond - depenseActuelle;
    }
}