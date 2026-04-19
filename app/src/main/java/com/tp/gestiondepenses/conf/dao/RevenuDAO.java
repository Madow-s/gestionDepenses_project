package com.tp.gestiondepenses.conf.dao;

import androidx.room.*;
import java.util.List;
import com.tp.gestiondepenses.conf.entity.Revenu;


@Dao
public interface RevenuDAO {

    @Insert
    void insert(Revenu revenu);

    @Update
    void update(Revenu revenu);

    @Delete
    void delete(Revenu revenu);

    @Query("SELECT * FROM revenus ORDER BY date DESC")
    List<Revenu> getAll();
}