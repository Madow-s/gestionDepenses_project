package com.tp.gestiondepenses.conf.dao;

import androidx.room.*;
import java.util.List;
import com.tp.gestiondepenses.conf.entity.Rubrique;


@Dao
public interface RubriqueDAO {

    @Insert
    void insert(Rubrique rubrique);

    @Update
    void update(Rubrique rubrique);

    @Delete
    void delete(Rubrique rubrique);

    @Query("SELECT * FROM rubriques")
    List<Rubrique> getAll();

    @Query("SELECT * FROM rubriques WHERE categorie_id = :categorieId")
    List<Rubrique> getByCategorie(int categorieId);
}