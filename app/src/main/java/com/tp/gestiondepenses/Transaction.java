package com.tp.gestiondepenses;

public class Transaction {
    private String description;
    private double montant;
    private String date;

    public Transaction(String description, double montant, String date) {
        this.description = description;
        this.montant = montant;
        this.date = date;
    }

    public String getDescription() { return description; }
    public double getMontant() { return montant; }
    public String getDate() { return date; }
}