package com.tp.gestiondepenses.conf.dao;

import androidx.room.*;
import java.util.List;
import com.tp.gestiondepenses.conf.entity.Depense;


@Dao
public interface DepenseDAO {

    @Insert
    void insert(Depense depense);

    @Update
    void update(Depense depense);

    @Delete
    void delete(Depense depense);

    @Query("SELECT * FROM depenses ORDER BY date DESC")
    List<Depense> getAll();

    @Query("SELECT * FROM depenses WHERE categorie_id = :categorieId")
    List<Depense> getByCategorie(int categorieId);
}