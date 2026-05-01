package com.tp.gestiondepenses.conf.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String username;
    public String password;
}