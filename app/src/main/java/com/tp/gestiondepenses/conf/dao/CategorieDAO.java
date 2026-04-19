package com.tp.gestiondepenses.conf.dao;

import androidx.room.*;
import java.util.List;
import com.tp.gestiondepenses.conf.entity.Categorie;

@Dao
public interface CategorieDAO {

    @Insert
    void insert(Categorie categorie);

    @Update
    void update(Categorie categorie);

    @Delete
    void delete(Categorie categorie);

    @Query("SELECT * FROM categories")
    List<Categorie> getAllCategories();

    @Query("SELECT * FROM categories WHERE id = :id")
    Categorie getCategorieById(int id);
}