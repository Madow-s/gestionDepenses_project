package com.tp.gestiondepenses.conf.dao;

import androidx.room.*;
import java.util.List;
import com.tp.gestiondepenses.conf.entity.Budget;

@Dao
public interface BudgetDAO {

    @Insert
    void insert(Budget budget);
    @Update
    void update(Budget budget);

    @Delete
    void delete(Budget budget);

    @Query("SELECT * FROM budgets")
    List<Budget> getAll();

    @Query("SELECT * FROM budgets WHERE categorie_id = :categorieId")
    List<Budget> getByCategorie(int categorieId);
}