package com.tp.gestiondepenses.conf.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "budgets")
public class Budget {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private Integer categorie_id;

    private double montant_plafond;

    private String periode;

    private int mois;

    private int annee;

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Integer getCategorie_id() { return categorie_id; }
    public void setCategorie_id(Integer categorie_id) { this.categorie_id = categorie_id; }

    public double getMontant_plafond() { return montant_plafond; }
    public void setMontant_plafond(double montant_plafond) { this.montant_plafond = montant_plafond; }

    public String getPeriode() { return periode; }
    public void setPeriode(String periode) { this.periode = periode; }

    public int getMois() { return mois; }
    public void setMois(int mois) { this.mois = mois; }

    public int getAnnee() { return annee; }
    public void setAnnee(int annee) { this.annee = annee; }
}