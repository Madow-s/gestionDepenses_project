package com.tp.gestiondepenses.conf.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ForeignKey;

@Entity(tableName = "rubriques")
public class Rubrique {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int categorie_id;

    private String nom;

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getCategorie_id() { return categorie_id; }
    public void setCategorie_id(int categorie_id) { this.categorie_id = categorie_id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
}