package com.tp.gestiondepenses.conf.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "depenses")
public class Depense {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int categorie_id;
    private Integer rubrique_id; // nullable

    private double montant;
    private long date;
    private String description;
    private String moyen_paiement;
    private long created_at;

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getCategorie_id() { return categorie_id; }
    public void setCategorie_id(int categorie_id) { this.categorie_id = categorie_id; }

    public Integer getRubrique_id() { return rubrique_id; }
    public void setRubrique_id(Integer rubrique_id) { this.rubrique_id = rubrique_id; }

    public double getMontant() { return montant; }
    public void setMontant(double montant) { this.montant = montant; }

    public long getDate() { return date; }
    public void setDate(long date) { this.date = date; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getMoyen_paiement() { return moyen_paiement; }
    public void setMoyen_paiement(String moyen_paiement) { this.moyen_paiement = moyen_paiement; }

    public long getCreated_at() { return created_at; }
    public void setCreated_at(long created_at) { this.created_at = created_at; }
}