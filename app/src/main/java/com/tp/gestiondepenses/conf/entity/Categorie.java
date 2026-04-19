package com.tp.gestiondepenses.conf.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "categories")
public class Categorie {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String nom;
    private String icone;
    private String couleur;
    private boolean est_defaut;

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getIcone() { return icone; }
    public void setIcone(String icone) { this.icone = icone; }

    public String getCouleur() { return couleur; }
    public void setCouleur(String couleur) { this.couleur = couleur; }

    public boolean isEst_defaut() { return est_defaut; }
    public void setEst_defaut(boolean est_defaut) { this.est_defaut = est_defaut; }
}