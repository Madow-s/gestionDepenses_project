package com.tp.gestiondepenses.conf.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String username;
    public String email;
    public String password;
    public String DateOB;
    public String phoneNumber;
    public String Bio;

    // Getter & Setter ID
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Date de naissance
    public String getDateOB() {
        return DateOB;
    }

    public void setDateOB(String dateOB) {
        DateOB = dateOB;
    }

    // Téléphone
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Bio
    public String getBio() {
        return Bio;
    }

    public void setBio(String bio) {
        Bio = bio;
    }
}